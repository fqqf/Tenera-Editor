package com.mygdx.game.tests.test4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.Monitor;

public class Main extends ApplicationLoop
{
 private Cave cave;

 public void create()
 {
  super.create();

  new Monitor();

  cave = new Cave("cave-level", new ExtendCoordinateGrid("level-coordinate-grid",10f), 100, 10);
 }

 public void drawGraphics()
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  cave.iterDraw(extrapolation);
 }

 @Override
 public void calcPhysics()
 {
  cave.iterPhys();
 }

 public void resize(int width, int height) { Monitor.instance.update(width, height); }
}
