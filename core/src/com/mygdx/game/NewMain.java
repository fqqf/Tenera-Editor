package com.mygdx.game;

import com.mygdx.game.ext.*;
import com.mygdx.game.ext.Scene;

public class NewMain extends ApplicationLoop
{
 private DarkVillageLevel darkVillage;

 public void create()
 {
  new View();

  darkVillage = new DarkVillageLevel("dark-village", new Field("level-coordinate-grid",20), 15, 15);
  darkVillage.drawGrid = true;
 }

 public void drawGraphics()
 {
  darkVillage.iterDraw(extrapolation);
 }

 public void resize(int width, int height) { Singletones.view.update(width, height); }
}
