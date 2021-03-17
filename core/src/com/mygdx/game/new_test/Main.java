package com.mygdx.game.new_test;

import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.new_test.worlds.Darkness;

public class Main extends ApplicationLoop
{
 Scene darkness, mountain, village, currentScene;

 @Override
 public void create()
 {
  super.create();

  darkness = new Darkness("dark-level",new ExtendCoordinateGrid("coordinate-grid", 10),1400,600);

  currentScene = darkness;
 }

 @Override
 public void drawGraphics()
 {
  currentScene.iterDraw(extrapolation);
 }

 @Override
 public void calcPhysics()
 {
  currentScene.iterPhys();
 }
}
