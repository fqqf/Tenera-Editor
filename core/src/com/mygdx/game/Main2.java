package com.mygdx.game;

import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.utils.Singletones;

public class Main2 extends ApplicationLoop
{
 private DarkForest darkVillage;

 public void create()
 {
  new Monitor();

  darkVillage = new DarkForest("dark-forest", new ExtendField("level-coordinate-grid",25f), 100, 25);
  darkVillage.drawGrid = true;
 }

 public void drawGraphics()
 {
  darkVillage.iterDraw(extrapolation);
 }

 public void resize(int width, int height) { Monitor.instance.update(width, height); }
}
