package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.Monitor;

public class Main extends ApplicationLoop
{
 City city;

 public void create()
 {
  super.create();

  new Monitor();

  city = new City("city-level", new ExtendCoordinateGrid("level-coordinate-grid", 10f), 100, 10);
 }

 @Override
 public void drawGraphics()
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  city.iterDraw(extrapolation);
 }

 @Override
 public void calcPhysics()
 {
  city.iterPhys();
 }

 public void resize(int width, int height) { Monitor.instance.update(width, height); }
}
