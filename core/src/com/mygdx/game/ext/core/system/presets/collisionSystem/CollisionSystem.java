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
   switch (collisionComponent.box.getType())
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
   world.update(cc.item, bp.position.x + cc.box.offset.x, bp.position.y + cc.box.offset.y, cc.box.width, cc.box.height);
  }
 }

 public CollisionSystem()
 {
  type = Type.PHYSICS_SYSTEM;
  priority = 2;
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
   if (cc.box.getType() != CollisionType.BODY) continue;

   Item<Actor> item = CollisionComponent.get(actorA).item;


   float moveToX = physics1.position.x + cc.box.offset.x;
   float moveToY = physics1.position.y + cc.box.offset.y;

   Response.Result result = world.move(item, moveToX, moveToY, collisionFilter);
   if (!result.projectedCollisions.isEmpty())
   {
    result.projectedCollisions.others.forEach(
      other ->
      {
       Actor collisionActor = (Actor) other.userData;
       CollisionComponent collisionComponent = CollisionComponent.get(collisionActor);
       switch (collisionComponent.box.getType())
       {
        case CollisionType.LIQUID:
         PhysicsComponent physics = PhysicsComponent.get(collisionActor);
         physics.velocity.scl(0.5f, 0.5f);
         break;
       }
      });


    DrawingComponent drawingComponent = DrawingComponent.get(actorA);
    if ( result.goalX != moveToX ) { drawingComponent.extrapolationX = false; drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;}
    if ( result.goalY != moveToY ) { drawingComponent.extrapolationY = false; drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;}


    result.projectedCollisions.items.forEach( item1-> { DrawingComponent.get((Actor)item1.userData).debugCollisionColor = Color.RED;}); //debug
    result.projectedCollisions.others.forEach( other-> { DrawingComponent.get((Actor)other.userData).debugCollisionColor = Color.RED;}); //debug
   }

   //todo вот тут я говорил что именно [VERY_BIG_FONT]ОТНИМАТЬ[/VERY_BIG_FONT] offset нужно, ну попробуй прибавить и расскажи что получилось...
   physics1.position.set(result.goalX - cc.box.offset.x, result.goalY - cc.box.offset.y);
  }
 }
}
