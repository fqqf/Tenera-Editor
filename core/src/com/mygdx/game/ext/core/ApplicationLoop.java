package com.mygdx.game.ext;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * This class stands for separation game loop in 3 parts:
 * Physics loop, graphic loop and handle input loop
 * Graphics and input (FPS) are called on max fps, depends is v-sync on or off
 * Physics are being called TICK_AMOUNT times for second (TPS)
 * Extrapolation value depends on TPS
 */

public class ApplicationLoop extends ApplicationAdapter
{
 Logger logger;
 public static final boolean DEBUG = false;

 private static final long SECOND_IN_NANO = 1_000_000_000;
 private static final long TICK_AMOUNT = 10;
 private static final float TICK_IN_NANO = SECOND_IN_NANO / (float)TICK_AMOUNT;
 private static final long MAX_NANO_TIME_FOR_LOW_FPS = (long)(TICK_IN_NANO - TICK_IN_NANO/15);

 public long realTime = TimeUtils.nanoTime(), renderDelta, inGameTime; // IN NANOSECONDS
 public long tick, nextTickTime, nextSecondTime, TPS, FPS;

 protected float extrapolation = 0.5f;
 private byte lowFpsCounter = 0;
 private final static byte MAX_FRAME_IGNORE_LOW_FPS = 2;
 @Override
 public void render()
 {
  FPS++;
  renderDelta = -(realTime - (realTime = TimeUtils.nanoTime())); // lastCallTime - curCallTime

  //TODO: Playing on 10 fps becomes not possible, because time stops
  //inGameTime += (renderDelta < 100_000_000) ? renderDelta : freeze(); // if window was on hold more for than a 0.1 sec, it freezes time for that moment
  if (renderDelta > MAX_NANO_TIME_FOR_LOW_FPS && ++lowFpsCounter > MAX_FRAME_IGNORE_LOW_FPS)
  {
   lowFpsHandler();
   lowFpsCounter = MAX_FRAME_IGNORE_LOW_FPS;
  }
  else
  {
   if ( lowFpsCounter > MAX_FRAME_IGNORE_LOW_FPS ) lowFpsCounter = 0;
   inGameTime += Math.min( renderDelta, TICK_IN_NANO );
  }

  if (inGameTime > nextTickTime)
  {
   nextTickTime += TICK_IN_NANO;
   tick++; TPS++;
   calcPhysics();
   //logger.info("Calling Physics");
  }

  extrapolation = (inGameTime-(nextTickTime-TICK_IN_NANO)) / (TICK_IN_NANO);
  handleInput();
  drawGraphics();

  if (inGameTime > nextSecondTime)
  {
   nextSecondTime += SECOND_IN_NANO;
   if (DEBUG) logger.info("FPS:"+FPS+" TPS:"+TPS);
   TPS = 0; FPS = 0;
  }
 }

 // FREEZES IN-GAME TIME, BUT RENDERING CONTINUES
 public int freeze()
 {
  if (DEBUG) logger.info("Time was frozen for "+renderDelta/1_000_000_000f+" sec"); return 0;
 }


 @Override
 public void create()
 {
  logger = new Logger("GAMELOOP", Logger.INFO);
  logger.info("Application cycle has launched successfully");
 }

 public void lowFpsHandler()
 {
  System.out.println("low fps!");
 }
 public void drawGraphics() { }
 public void calcPhysics() { }
 public void handleInput() { }

 private long pauseStartTime = 0;
 @Override
 public void resume()
 {
  long delta = TimeUtils.nanoTime() - pauseStartTime;
  System.out.println("resume after pause: " + delta / SECOND_IN_NANO);
  nextTickTime += delta;
  inGameTime += delta;
 }

 @Override
 public void pause()
 {
  System.out.println("pause");
  pauseStartTime = TimeUtils.nanoTime();
 }
}


/* EXAMPLE OF EXTRAPOLATION */
 /*
 @Override
 public void create()
 {
  texture = new Texture(Gdx.files.internal("badlogic.jpg"));
  batch = new SpriteBatch();

  logger.info("Application cycle has launched successfully");
 }

 Texture texture;
 SpriteBatch batch;

 public void drawGraphic()
 {
  Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  batch.begin();
  batch.draw(texture,pos+sp*extrapolation,0);
  batch.end();
 }

 float sp=5, pos, lastpos;
 public void calcPhysic()
 {
  lastpos = pos;
  pos = lastpos+sp;
 }

 public void handleInput()
 {

 }
*/
// END OF EXAMPLE
