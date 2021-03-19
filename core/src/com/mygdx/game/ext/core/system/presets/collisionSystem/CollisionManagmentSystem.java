package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.*;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.system.System;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class CollisionManagmentSystem extends System
{
 public static World<Actor> world = new World<>();
 @Override
 public void addActor(Actor... actors)
 {
  super.addActor(actors);
  for (Actor actor1 : actors)
  {
   BasePhysicsComponent ph = BasePhysicsComponent.get(actor1);
   Item<Actor> item = CollisionComponent.get(actor1).item;
   float x = ph.position.x + CollisionComponent.get(actor1).box.offset.x;
   float y = ph.position.y + CollisionComponent.get(actor1).box.offset.y;
   float width = CollisionComponent.get(actor1).box.width;
   float height = CollisionComponent.get(actor1).box.height;
   world.add(item, x, y, width,height);
   Rect rect = world.getRect(item);
   java.lang.System.out.println("" + actor1.getClass().getSimpleName() +" x="+x+",y="+y+",w="+width+",h="+height);
   java.lang.System.out.println("in World x="+rect.x+",y="+rect.y+",w="+rect.w+",h="+rect.h);
  }
 }

 public CollisionManagmentSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 99;
 }

 private final Vector2 vector2 = new Vector2();
 private final Array<Actor> collisions = new Array<>(5);
 private BasePhysicsComponent physics;
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

   Item<Actor> item = CollisionComponent.get(actorA).item;
   physics = BasePhysicsComponent.get(actorA);


   Response.Result r = world.move(item, boxA.x, boxA.y, CollisionFilter.defaultFilter );
   if ( !r.projectedCollisions.isEmpty() )
   {
    DrawingComponent drawingComponent = DrawingComponent.get(actorA);
    if ( r.goalX != boxA.x ) { drawingComponent.extrapolationX = false; drawingComponent.extrapolationOffNanoX = ApplicationLoop.instance.nextTickTime;}
    if ( r.goalY != boxA.y ) { drawingComponent.extrapolationY = false; drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;}
   }
   physics.position.set(r.goalX,r.goalY);

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
    basePhysicsComponent.position.x,// + basePhysicsComponent.velocity.x * extr,
    basePhysicsComponent.position.y// + basePhysicsComponent.velocity.y * extr
  );
  return box;
 }

 private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
 {
  physics = BasePhysicsComponent.get(actorBody);
  physics.velocity.scl(0.5f, 0.5f);
 }
}
