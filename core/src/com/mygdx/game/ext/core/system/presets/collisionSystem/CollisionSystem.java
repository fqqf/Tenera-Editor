package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.*;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;

public class CollisionSystem extends System
{
 private static final CollisionFilter collisionFilter = new CollisionFilter() {
  @Override
  public Response filter(Item item, Item other)
  {
   Actor actorB = (Actor)other.userData;
   CollisionComponent collisionComponent = CollisionComponent.get(actorB);
   switch (collisionComponent.collisionType)
   {
    case CollisionType.PLATFORM:
     float speedY = PhysicsComponent.get((Actor) item.userData).velocity.y;
     Rect otherRect = world.getRect(other);
     Rect itemRect = world.getRect(item);
     float topPlatform = otherRect.y + otherRect.h;
     if (itemRect.y - speedY < topPlatform) return Response.cross;
     else return Response.slide;
    case CollisionType.LIQUID:
     return Response.cross;
    default:
     return Response.slide;
   }
  }
 };
 public static World<Actor> world = new World<>();
 @Override
 public void addActor(Actor... actors)
 {
  super.addActor(actors);
  for (Actor actor1 : actors) world.add(CollisionComponent.get(actor1).item, 0, 0, 0,0);
  updateActor(actors);
 }
 @Override
 public void remActor(Actor... actors)
 {
  super.remActor(actors);
  for (Actor actor1 : actors)
  {
   Item<Actor> item = CollisionComponent.get(actor1).item;
   world.remove(item);
  }
 }

 public void updateActor(Actor... actors)
 {
  for (Actor actor : actors)
  {
   BodyPropertiesComponent bp = BodyPropertiesComponent.get(actor);
   CollisionComponent cc = CollisionComponent.get(actor);
   world.update(cc.item, bp.position.x, bp.position.y, bp.size.x, bp.size.y);
  }
 }

 public CollisionSystem()
 {
  type = Type.PHYSICS_SYSTEM;
  priority = 10;
 }

 public void handle()
 {
  // logger.info("Collision System");
  for (int i = 0; i < assignedActors.size; i++)
  {
   Actor actorA = assignedActors.get(i);
   PhysicsComponent physics1 = PhysicsComponent.get(actorA);
   if (physics1.velocity.isZero())continue;

   CollisionComponent cc = CollisionComponent.get(actorA);
   if (cc.collisionType != CollisionType.BODY) continue;

   Item<Actor> item = CollisionComponent.get(actorA).item;

   float targetX = physics1.position.x;
   float targetY = physics1.position.y;

   Response.Result result = world.move(item, targetX, targetY, collisionFilter);
   if (!result.projectedCollisions.isEmpty())
   {
    result.projectedCollisions.others.forEach(
      other ->
      {
       Actor collisionActor = (Actor) other.userData;
       CollisionComponent collisionComponent = CollisionComponent.get(collisionActor);
       switch (collisionComponent.collisionType)
       {
        case CollisionType.LIQUID:
         PhysicsComponent physics = PhysicsComponent.get(collisionActor);
         physics.velocity.scl(0.5f, 0.5f);
         break;
       }
      });


    DrawingComponent drawingComponent = DrawingComponent.get(actorA);
    if ( result.goalX != targetX ) { drawingComponent.extrapolationX = false; drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;}
    if ( result.goalY != targetY ) { drawingComponent.extrapolationY = false; drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime; }

    result.projectedCollisions.items.forEach( item1-> { DrawingComponent.get((Actor)item1.userData).debugCollisionColor = Color.RED;}); //debug
    result.projectedCollisions.others.forEach( other-> { DrawingComponent.get((Actor)other.userData).debugCollisionColor = Color.RED;}); //debug
   }
   physics1.position.set(result.goalX, result.goalY);
  }
 }
}
