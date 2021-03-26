package com.mygdx.game.new_game;

import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.movement.JumpComponent;
import com.mygdx.game.ext.core.components.presets.movement.MovingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.new_game.entities.Alice;

public class AliceBehaviourSystem extends System
{
 private boolean UP, LEFT, RIGHT;

 private static Alice alice;

 public static void setAlice(Alice alic)
 {
  alice = alic;
 }

 public static Alice getAlice()
 {
  return alice;
 }

 public AliceBehaviourSystem()
 {}

 {
  type = System.Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
  priority = 1;
 }

 @Override
 public void handle()
 {
  if (alice!=null) behave();
 }
 @Override
 protected void behave()
 {
  UP    = KeyBoardSystem.UP; // TODO: PUT THIS BLOCK
  LEFT  = KeyBoardSystem.LEFT;
  RIGHT = KeyBoardSystem.RIGHT;


  if (UP) JumpComponent.get(alice).jump();
  if (LEFT) { MovingComponent.get(alice).move(-1); if (!RIGHT) DrawingComponent.get(alice).flipX = true;}
  if (RIGHT) { MovingComponent.get(alice).move(1); if (!LEFT) DrawingComponent.get(alice).flipX = false;}

  KeyBoardSystem.UP = false;
  KeyBoardSystem.LEFT = false;
  KeyBoardSystem.RIGHT = false;

  DrawingComponent drawingComponent = DrawingComponent.get(alice);
  DrawingComponent fightAnimationDrawingComponent = DrawingComponent.get(alice.fightAnimation);

  fightAnimationDrawingComponent.flipX = DrawingComponent.get(alice).flipX;
  fightAnimationDrawingComponent.draw = false;

  fightAnimationDrawingComponent.offset.x = (fightAnimationDrawingComponent.flipX) ? -2.9f : 0.5f;

  if (ApplicationLoop.instance.inGameTime<alice.invisibilityStartTime+1_000_000_000L) drawingComponent.draw = !drawingComponent.draw;
  else drawingComponent.draw = true;
 }
}
