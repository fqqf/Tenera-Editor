package com.mygdx.game.new_test;

import com.mygdx.game.ext.core.system.presets.*;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionManagmentSystem;

public class Systems
{
 public static CollisionManagmentSystem collisionManagmentSystem = new CollisionManagmentSystem();
 public static ControlSystem controlSystem = new ControlSystem();
 public static PhysicsSystem physicsSystem = new PhysicsSystem();
 public static DrawingSystem drawingSystem = new DrawingSystem();
 public static AnimationSystem animationSystem = new AnimationSystem();
}
