package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.system.presets.ControlSystem;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.ext.core.system.presets.ExtrapolationDrawingSystem;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionManagmentSystem;

/** This scene already includes AABB physics collision system, Event system, and drawing by layers */
public class GameScene extends Scene
{
 public GameScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addSystem(collisionManagmentSystem, controlSystem, extrapolationDrawingSystem, physicsSystem);
 }


 @Override
 public void iterPhys()
 {
  super.iterPhys();
 }

 public static CollisionManagmentSystem collisionManagmentSystem = new CollisionManagmentSystem();
 public static ControlSystem controlSystem = new ControlSystem();
 public static ExtrapolationDrawingSystem extrapolationDrawingSystem = new ExtrapolationDrawingSystem();
 public static PhysicsSystem physicsSystem = new PhysicsSystem();
 public static DrawingSystem drawingSystem = new DrawingSystem();
}
