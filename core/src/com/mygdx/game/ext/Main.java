package com.mygdx.game.ext;

import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.tests.test3.DarkForest;

public class Main extends ApplicationLoop
{
 private Cave cave;

 public void create()
 {
  new Monitor();

  cave = new Cave("cave-level", new ExtendField("level-coordinate-grid",10f), 100, 10);
  cave.drawGrid = true;
 }

 public void drawGraphics()
 {
  cave.iterDraw(extrapolation);
 }

 public void resize(int width, int height) { Monitor.instance.update(width, height); }
}
