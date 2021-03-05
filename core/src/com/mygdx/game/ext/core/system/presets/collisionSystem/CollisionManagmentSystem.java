package com.mygdx.game.ext.core.components.presets.collisionSystem;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.group.Group;
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

  switch (boxB.getType())
  {
   case CollisionType.SOLID:
    handleBodySolid(actorA, actorB);
    break;
   case CollisionType.LIQUID:
    handleBodyLiquid(actorA, actorB);
    break;
  }
 }

 private final Vector2 centerA = new Vector2(), centerB = new Vector2();
 private final Vector3 vector3 = new Vector3();

 private void handleBodySolid(Actor actorBody, Actor actorSolid)
 {
  boxA.getCenter(centerA);
  boxB.getCenter(centerB);

  float deltaX = centerA.x - centerB.x;
  float deltaY = centerA.y - centerB.y;

  vector3.x = (boxA.width - Math.abs(deltaX)) * Math.signum(deltaX);
  vector3.y = (boxA.height - Math.abs(deltaY)) * Math.signum(deltaY);

  physics = BasePhysicsComponent.get(actorBody);

  if (Math.abs(deltaX) < Math.abs(deltaY))
  {
   physics.position.y += vector3.y;
   physics.velocity.y = 0;
  } else
  {
   physics.position.x += vector3.x;
   physics.velocity.x = 0;
  }
 }

 private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
 {
  physics = BasePhysicsComponent.get(actorBody);

  physics.velocity.scl(0.5f, 0.5f);
 }

 BoundingBox rectangleComponent;
}
