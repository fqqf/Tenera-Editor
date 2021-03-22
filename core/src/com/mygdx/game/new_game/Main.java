package com.mygdx.game.new_game;

import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class Main extends ApplicationLoop
{
 public static Scene firstAliceLevel, currentScene;

 @Override
 public void create()
 {
  super.create();
  System.out.println("here?");
  firstAliceLevel = new FirstAliceLevel("first-alice-level",new ExtendCoordinateGrid("coordinate-grid", 12),100,100);



  currentScene = firstAliceLevel;
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
