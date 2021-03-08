package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;

public class CollisionManagmentSystem extends System
{
 public CollisionManagmentSystem()
 {
  type = Type.PHYSICS_SYSTEM;
 }

 private BoundingBox boxA, boxB;// Due to be managed by another class

 public void handle()
 {
  // logger.info("Collision handle");
  for (int i = 0; i < assignedActors.size; i++)
  {
   Actor actorA = assignedActors.get(i);
   boxA = getBox(actorA);
   for (Actor actorB : assignedActors)
   {
    if (actorA == actorB) continue;
    boxB = getBox(actorB);
    if (boxA.overlaps(boxB)) handleCollision(actorA, actorB);
   }
  }
 }

 private BoundingBox getBox(Actor actor) { return CollisionComponent.get(actor).box; }

 private BasePhysicsComponent physics;
 private CollisionComponent collision;

 private void handleCollision(Actor actorA, Actor actorB)
 {

  if (boxA.getType() != CollisionType.BODY) return;
  // logger.info("ACTOR A (BODY) "+boxA.getSize(new Vector2()).toString());
  // logger.info("ACTOR B (SOLID) "+boxB.getSize(new Vector2()).toString());

  switch (boxB.getType())
  {
   case CollisionType.SOLID:
    handleBodySolid(actorA);
    break;
   case CollisionType.LIQUID:
    handleBodyLiquid(actorA, actorB);
    break;
  }
 }

 private final Vector2 centerA = new Vector2(), centerB = new Vector2();

 private void handleBodySolid(Actor actorBody)
 {
  // logger.info("handleBodySolid");
  DrawingComponent drawingComponent = DrawingComponent.get(actorBody);
  boxA.getCenter(centerA);
  boxB.getCenter(centerB);

  float deltaX = centerA.x - centerB.x;
  float deltaY = centerA.y - centerB.y;

  physics = BasePhysicsComponent.get(actorBody);
  if (Math.abs(deltaX) < Math.abs(deltaY))
  {
   physics.position.y += ((boxB.height/2 + boxA.height/2) - Math.abs(deltaY)) * Math.signum(deltaY);
   physics.velocity.y = 0;
   drawingComponent.extrapolationY = false;
   drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;

  } else
  {
   physics.position.x += ((boxB.width/2 + boxA.width/2) - Math.abs(deltaX)) * Math.signum(deltaX);
   physics.velocity.x = 0;
   drawingComponent.extrapolationX = false;
   drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;

  }
 }

 private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
 {
  physics = BasePhysicsComponent.get(actorBody);

  physics.velocity.scl(0.5f, 0.5f);
 }
}
