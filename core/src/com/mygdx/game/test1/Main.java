package com.mygdx.game.test1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.scene.StandartActor;
import com.mygdx.game.ext.scene.StandartScene;

public class Main extends ApplicationLoop
{
 Monitor monitor;
 ExtendField gameInterface, gameScreen;

 StandartScene testLevel;

 StandartActor hero, monster;

 @Override
 public void create()
 {
  super.create();

  monitor = new Monitor();
 // gameInterface = new Field("Menu Field", 7);
  gameScreen = new ExtendField("Level Field", 7);
  testLevel = new StandartScene("Test Level", gameScreen, 100, 100);

  hero = new Hero()
          .setDrawLayer(120)
          .scene(testLevel, "hero")
          .position(1,0)
          .size(0.3f*2,0.8f*2)
          .texture(new Texture(Gdx.files.internal("hero.png")));

  testLevel.addObject(new StandartActor()
                  .setDrawLayer(1)
                  .size(100,10)
      .texture(new Texture(Gdx.files.internal("map.png")))
      .visibility(true)
    ,"map");

  testLevel.addObject(new StandartActor()
                  .setDrawLayer(1)
                  .position(3,0)
    .size(1,1)
    .texture(new Texture(Gdx.files.internal("badlogic.jpg")))
    ,"box1");

  testLevel.addObject(new StandartActor()
                  .setDrawLayer(0)
      .position(15,0)
      .size(6,8)
      .texture(new Texture(Gdx.files.internal("castle.png")))
    ,"castle");

  testLevel.addObject(new StandartActor()
          .setDrawLayer( 2 )
      .position(8.15f-0.5f,3.5f-0.5f)
      .size(1,1)
      .texture(new Texture(Gdx.files.internal("badlogic.jpg")))
    ,"box3");

  gameScreen.cameraController.move(0,0);
 }

 // Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
 // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//  levelsField.debug(Color.GOLD, Color.GREEN, Color.CYAN);
 // menuField.debug(Color.RED, Color.BLACK, Color.CYAN);

 @Override
 public void drawGraphics()
 {
  Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  testLevel.iterDraw(extrapolation);

  //gameInterface.debug(Color.BROWN, Color.BROWN, Color.BROWN);
  //gameScreen.debug(Color.LIME, Color.RED, Color.CYAN);
 }

 @Override
 public void handleInput()
 {
  testLevel.iterInput();
 }

 @Override
 public void calcPhysics()
 {
  testLevel.iterPhys();
 }

 @Override
 public void dispose()
 {
 }

 @Override
 public void resize(int width, int height)
 {
  monitor.update(width, height);
 }
}
