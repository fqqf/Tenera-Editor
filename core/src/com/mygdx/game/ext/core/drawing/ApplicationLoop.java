package com.mygdx.game.ext.core.drawing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.ext.core.drawing.view.Monitor;

/**
 * This class stands for separation game loop in 3 parts:
 * Physics loop, graphic loop and handle input loop
 * Graphics and input (FPS) are called on max fps, depends is v-sync on or off
 * Physics are being called TICK_AMOUNT times for second (TPS)
 * Extrapolation value depends on TPS
 */

public abstract class ApplicationLoop extends ApplicationAdapter
{
 // TODO : Interface, like View
 public static ApplicationLoop instance;

 private Logger logger;
 private static final boolean DEBUG = false;

 private final long SECOND_IN_NANO = 1_000_000_000;
 private final long TICK_AMOUNT = 10;
 private final long TICK_IN_NANO = SECOND_IN_NANO / TICK_AMOUNT;

 public long realTime = TimeUtils.nanoTime(), renderDelta, inGameTime; // IN NANOSECONDS
 public long tick, nextTickTime, nextSecondTime, TPS, FPS;

 public float extrapolation = 0.5f;

 /** If you feel the need to override this method, please call super.render() **/
 @Override
 public void render()
 {
  if (instance == null) {logger.error("Call super.create() in create method first!"); System.exit(1);}
  FPS++;
  renderDelta = -(realTime - (realTime = TimeUtils.nanoTime())); // lastCallTime - curCallTime

  //TODO: Playing on 10 fps becomes not possible, because time stops
  inGameTime += (renderDelta < 100_000_000) ? renderDelta : freeze(); // if window was on hold more for than a 0.1 sec, it freezes time for that moment

  if (inGameTime > nextTickTime)
  {
   nextTickTime += TICK_IN_NANO;
   tick++; TPS++;
   calcPhysics();
   //logger.info("Calling Physics");
  }

  extrapolation = (inGameTime-(nextTickTime-TICK_IN_NANO))/(TICK_IN_NANO+0f);
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
  logger.info("Application cycle has launched successfully");
  instance = this;
 }

 {
  logger = new Logger("GAMELOOP", Logger.INFO);
 }

 public void drawGraphics()
 {

 }

 public void calcPhysics()
 {

 }

 public void handleInput()
 {

 }

 @Override
 public void resize(int width, int height)
 {
  Monitor.instance.update(width, height);
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
