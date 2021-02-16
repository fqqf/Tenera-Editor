package com.mygdx.game.test2;

import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.core.Monitor;

public class NewMain extends ApplicationLoop
{
 private DarkVillageLevel darkVillage;

 public void create()
 {
  new Monitor();

  darkVillage = new DarkVillageLevel("dark-village", new ExtendField("level-coordinate-grid",25), 100, 25);
  darkVillage.drawGrid = true;
 }

 public void drawGraphics()
 {
  darkVillage.iterDraw(extrapolation);
 }

 public void resize(int width, int height) { Monitor.instance.update(width, height); }
}
