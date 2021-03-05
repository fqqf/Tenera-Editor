package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

/** This scene already includes AABB physics collision system, Event system, and drawing by layers */
public class GameScene extends Scene
{
 public GameScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }


 @Override
 public void iterPhys()
 {
  super.iterPhys();
 }
}
