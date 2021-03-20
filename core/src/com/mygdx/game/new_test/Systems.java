package com.mygdx.game.new_test;

import com.mygdx.game.ext.core.system.presets.*;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;
import com.mygdx.game.new_test.systems.HeroControlMovement;

public class Systems
{
 public static AnimationSystem animationSystem = new AnimationSystem();
 public static CollisionSystem collisionSystem = new CollisionSystem();
 public static HeroControlMovement controlSystem = new HeroControlMovement();
 public static PhysicsSystem physicsSystem = new PhysicsSystem();
 public static DrawingSystem drawingSystem = new DrawingSystem();
}
