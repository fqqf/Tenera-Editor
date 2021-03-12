package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
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
  type = Type.RENDER_SYSTEM;
  priority = 1;
 }

 private BoundingBox boxA, boxB;

 public void handle()
 {
  // logger.info("Collision System");
  float extr = ApplicationLoop.instance.extrapolation;

  for (int i = 0; i < assignedActors.size; i++)
  {
   Actor actorA = assignedActors.get(i);
   boxA = getSynchronizedBox(actorA,extr);
   if (boxA.getType()!=CollisionType.BODY) continue;

   for (Actor actorB : assignedActors)
   {
    if (actorA == actorB) continue;
    boxB = getSynchronizedBox(actorB,extr);
    if (boxA.overlaps(boxB))
    {
     handleCollision(actorA, actorB);
     BasePhysicsComponent.get(actorA).color = Color.RED;
     BasePhysicsComponent.get(actorB).color = Color.RED;
    }
   }
  }
 }

 private BoundingBox getBox(Actor actor)
 {
  return CollisionComponent.get(actor).box;
 }
 private BoundingBox getSynchronizedBox(final Actor actor, final float extr)
 {
  BoundingBox box = CollisionComponent.get(actor).box;
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);
  box.setPosition(
    basePhysicsComponent.position.x + basePhysicsComponent.velocity.x * extr,
    basePhysicsComponent.position.y + basePhysicsComponent.velocity.y * extr
  );
  return box;
 }

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

  // DrawingComponent drawingComponent = DrawingComponent.get(actorBody);
  boxA.getCenter(centerA);
  boxB.getCenter(centerB);

  float deltaCenterX = centerA.x - centerB.x;
  float deltaCenterY = centerA.y - centerB.y;
  float y = ((boxB.halfHeight + boxA.halfHeight) - Math.abs(deltaCenterY));
  float x = ((boxB.halfWidth + boxA.halfWidth) - Math.abs(deltaCenterX));

  physics = BasePhysicsComponent.get(actorBody);
  float absX = Math.abs(x);
  float absY = Math.abs(y);
  if (absX > absY)
  {
   // physics.position.y += absY * Math.signum(deltaCenterY);
   physics.position.y = Math.signum(deltaCenterY) > 0 ? boxB.getTop() : boxB.y - boxA.height;
   physics.velocity.y = 0;


   // drawingComponent.extrapolationY = false;
   // drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;
  }
  else
  {
   // physics.position.x += absX * Math.signum(deltaCenterX);
   physics.position.x = Math.signum(deltaCenterX) > 0 ? boxB.getRight() : boxB.x - boxA.width;
   physics.velocity.x = 0;
   // drawingComponent.extrapolationX = false;
   // drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;
  }
 }

 private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
 {
  physics = BasePhysicsComponent.get(actorBody);
  physics.velocity.scl(0.5f, 0.5f);
 }
}
