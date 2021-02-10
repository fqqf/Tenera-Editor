package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.*;

public class Main extends ApplicationLoop
{
 View view;
 Field gameInterface, gameScreen;

 Scene testLevel;

 Dobject hero, monster;

 @Override
 public void create()
 {
  super.create();

  view = new View();
 // gameInterface = new Field("Menu Field", 7);
  gameScreen = new Field("Level Field", 7);
  testLevel = new Scene("Test Level", gameScreen);
  hero = new Hero()
    .setScene(testLevel, "hero")
    .setPosition(1,0)
    .setSize(0.3f*2,0.8f*2)
    .setTexture(new Texture(Gdx.files.internal("hero.png")));
  testLevel.addObject(new Dobject()
    .setPosition(3,0)
    .setSize(1,1)
    .setTexture(new Texture(Gdx.files.internal("badlogic.jpg")))
    ,"box1");
  testLevel.addObject(new Dobject()
      .setPosition(6,0)
      .setSize(2,1)
      .setTexture(new Texture(Gdx.files.internal("badlogic.jpg")))
    ,"box2");
  testLevel.addObject(new Dobject()
      .setPosition(8.15f-0.5f,3.5f-0.5f)
      .setSize(1,1)
      .setTexture(new Texture(Gdx.files.internal("badlogic.jpg")))
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
  gameScreen.debug(Color.LIME, Color.RED, Color.CYAN);
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
  view.update(width, height);
 }
}
