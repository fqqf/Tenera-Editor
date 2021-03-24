package com.mygdx.game.new_game;

import com.mygdx.game.ext.core.system.presets.AnimationSystem;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;

public class Systems
{
 public static AnimationSystem animationSystem = new AnimationSystem();
 public static CollisionSystem collisionSystem = new CollisionSystem();
 public static PhysicsSystem physicsSystem = new PhysicsSystem();
 public static DrawingSystem drawingSystem = new DrawingSystem();
 public static KeyBoardSystem keyBoardSystem = new KeyBoardSystem();
 public static AliceBehaviourSystem aliceBehaviourSystem = new AliceBehaviourSystem();
}
