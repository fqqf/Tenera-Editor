package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
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
  type = Type.PHYSICS_SYSTEM;
  priority = 99;
 }

 private final Vector2 vector2 = new Vector2();

 private BoundingBox boxA, boxB;
 private final Rectangle resultBox = new Rectangle();
 public void handle()
 {
  // logger.info("Collision System");
  float extr = ApplicationLoop.instance.extrapolation;

  for (int i = 0; i < assignedActors.size; i++)
  {
   Actor actorA = assignedActors.get(i);
   CollisionComponent cc = CollisionComponent.get(actorA);
   boxA = getSynchronizedBox(actorA);
   if (boxA.getType()!=CollisionType.BODY) continue;

   BasePhysicsComponent physics = BasePhysicsComponent.get(actorA);

   float moveToX = physics.position.x + cc.box.offset.x;
   float moveToY = physics.position.y + cc.box.offset.y;

   resultBox.set(moveToX, moveToY, boxA.width,boxA.height);

   for (Actor actorB : assignedActors)
   {
    if (actorA == actorB) continue;
    boxB = getSynchronizedBox(actorB);
    if (resultBox.overlaps(boxB))
    {
     handleCollision(resultBox, actorA, actorB);
     DrawingComponent drawingComponent = DrawingComponent.get(actorA);

     if (resultBox.x != moveToX) { drawingComponent.extrapolationX = false; drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;}
     if (resultBox.y != moveToY) { drawingComponent.extrapolationY = false; drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;}

     BasePhysicsComponent.get(actorA).color = Color.RED;
     BasePhysicsComponent.get(actorB).color = Color.RED;
     boxA.setPosition(resultBox.x, resultBox.y);
    }
   }
   BasePhysicsComponent.get(actorA).position.x = resultBox.x - boxA.offset.x;
   BasePhysicsComponent.get(actorA).position.y = resultBox.y - boxA.offset.y;
  }
 }

 private BoundingBox getSynchronizedBox(final Actor actor)
 {
  BoundingBox box = CollisionComponent.get(actor).box;
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);
  box.setPosition(
    basePhysicsComponent.position.x + box.offset.x,
    basePhysicsComponent.position.y + box.offset.y
  );
  return box;
 }

 //private BasePhysicsComponent physics;
 private CollisionComponent collision;

 private void handleCollision(final Rectangle resultBox, Actor actorA, Actor actorB)
 {
  if (boxA.getType() != CollisionType.BODY) return;
  switch (boxB.getType())
  {
   case CollisionType.PLATFORM:
    float speedY = BasePhysicsComponent.get(actorA).velocity.y;
    float topPlatform = boxB.getTop();
    if (boxA.y - speedY < topPlatform)
    {
     if (boxA.y - speedY > topPlatform) handleBodySolid(resultBox, actorA);// collisions.add(actorB);
     break;
    }
    case CollisionType.SOLID:
    // collisions.add(actorB);
    handleBodySolid(resultBox, actorB);
    break;
   case CollisionType.LIQUID:
    handleBodyLiquid(actorA, actorB);
    break;
  }
 }

 private final Vector2 centerA = new Vector2(), centerB = new Vector2();

 private void handleBodySolid(final Rectangle resultBox, final Actor solidActor)
 {
  // logger.info("handleBodySolid");
  //DrawingComponent drawingComponent = DrawingComponent.get(bodyActor);

  resultBox.getCenter(centerA);
  boxB.getCenter(centerB);

  float deltaCenterX = centerA.x - centerB.x;
  float deltaCenterY = centerA.y - centerB.y;
  float y = ((boxB.halfHeight + boxA.halfHeight) - Math.abs(deltaCenterY));
  float x = ((boxB.halfWidth + boxA.halfWidth) - Math.abs(deltaCenterX));

  //physics = BasePhysicsComponent.get(bodyActor);

  float absX = Math.abs(x);
  float absY = Math.abs(y);
  if (absX > absY)
  {
   float signum = Math.signum(deltaCenterY);

   //if (Math.abs(vector2.y) < absY) vector2.y = absY * signum;
   //physics.position.y += absY * signum;

   // physics.position.y = signum > 0 ? boxB.getTop() : boxB.y - boxA.height;

   resultBox.y = signum > 0 ? boxB.getTop() : boxB.y - resultBox.height;

   //boxA.y = (signum > 0 ? boxB.getTop() : boxB.y - boxA.height) + boxA.offset.y;

   // physics.velocity.y = 0;

   //if (vector2.y < positionY) vector2.y = positionY;

   //vector2.y = Math.max(vector2.y, valueY);

   // if (signum != Math.signum(physics.velocity.y)) physics.velocity.y = 0;

   //drawingComponent.extrapolationY = false;
   //drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;
  }
  else
  {
   float signum = Math.signum(deltaCenterX);
   //if (Math.abs(vector2.x) < absY)vector2.x = absX * signum;

    //physics.position.x += absX * signum;

   //physics.position.x = signum > 0 ? boxB.getRight() : boxB.x - boxA.width;

   resultBox.x = signum > 0 ? boxB.getRight() : boxB.x - resultBox.width;

   //boxA.x += (absX * signum) * ApplicationLoop.instance.extrapolation;
   //boxA.x = (signum > 0 ? boxB.getRight() : boxB.x - boxA.width) + boxA.offset.x;

   //physics.velocity.x = 0;
   // vector2.x += signum > 0 ? boxB.getRight() : boxB.x - boxA.width;
   // float positionX = signum > 0 ? boxB.getRight() : boxB.x - boxA.width;
   // if (vector2.x > positionX) vector2.x = positionX;

   // if (signum != Math.signum(physics.velocity.x)) physics.velocity.x = 0;

    //drawingComponent.extrapolationX = false;
    //drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;
  }
 }

 private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
 {
  BasePhysicsComponent.get(actorBody).velocity.scl(0.5f, 0.5f);
 }
}
