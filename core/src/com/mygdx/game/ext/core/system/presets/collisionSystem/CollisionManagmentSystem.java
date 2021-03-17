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
 private final World<Actor> world = new World<>();
 private ShapeDrawer shapeDrawer = new ShapeDrawer(Monitor.instance.getBatch(), new TextureRegion(new Texture(Gdx.files.internal("white.jpg"))));
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
  }
 }

 public CollisionManagmentSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 99;
 }

 private final Vector2 vector2 = new Vector2();
 private final Array<Actor> collisions = new Array<>(5);

 private BoundingBox boxA, boxB;
 public void handle()
 {
  for (Item item : world.getItems())
  {
   shapeDrawer.setColor(Color.BLUE);
   shapeDrawer.setDefaultLineWidth(0.01f);
   Rect rect = world.getRect(item);
   Monitor.instance.getBatch().begin();
   shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
   Monitor.instance.getBatch().end();
  }
  // logger.info("Collision System");
  float extr = ApplicationLoop.instance.extrapolation;

  for (int i = 0; i < assignedActors.size; i++)
  {

   Actor actorA = assignedActors.get(i);
   boxA = getSynchronizedBox(actorA,extr);
   if (boxA.getType()!=CollisionType.BODY) continue;

   Item<Actor> item = CollisionComponent.get(actorA).item;
   physics = BasePhysicsComponent.get(actor);
   Response.Result r = world.move(item, physics.position.x, physics.position.y, CollisionFilter.defaultFilter );
   java.lang.System.out.println("connect " +r.projectedCollisions.size());
   physics.position.set(r.goalX,r.goalY);


  if (true)continue;
   collisions.clear();
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
   if (!collisions.isEmpty()) handleBodySolid(actorA, collisions);
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

 private BasePhysicsComponent physics;
 private CollisionComponent collision;

 private void handleCollision(Actor actorA, Actor actorB)
 {
  if (boxA.getType() != CollisionType.BODY) return;
  switch (boxB.getType())
  {
   case CollisionType.PLATFORM:
    float speedY = BasePhysicsComponent.get(actorA).velocity.y;
    float topPlatform = boxB.getTop();
    if (boxA.y - speedY < topPlatform)
    {
     if (boxA.y - speedY > topPlatform) collisions.add(actorB);// handleBodySolid(actorA);
     break;
    }
    case CollisionType.SOLID:
    collisions.add(actorB);
    // handleBodySolid(actorA);
    break;
   case CollisionType.LIQUID:
    handleBodyLiquid(actorA, actorB);
    break;
  }
 }

 private final Vector2 centerA = new Vector2(), centerB = new Vector2(), out = new Vector2(), result = new Vector2();

 private void handleBodySolid(Actor actorBody, Array<Actor> actors)
 {
  Vector2 velocity = BasePhysicsComponent.get(actorBody).velocity;
  result.setZero();
  for (int i = 0; i < actors.size; i++)
  {
   getVectorOut( boxA, getBox(actors.get(i)), out);
   if (Math.signum(velocity.x) < 0 && result.x < out.x) result.x = out.x;
   else if (Math.signum(velocity.x) > 0 && result.x > out.x) result.x = out.x;
   if (Math.signum(velocity.y) < 0 && result.y < out.y) result.y = out.y;
   else if (Math.signum(velocity.y) > 0 && result.y > out.y) result.y = out.y;
  }
  BasePhysicsComponent.get(actorBody).position.add(result);
  getBox(actorBody).setPosition(BasePhysicsComponent.get(actorBody).position);
 }
 private void getVectorOut(final BoundingBox boxA, final BoundingBox boxB, final Vector2 out)
 {
  out.setZero();
  boxA.getCenter(centerA);
  boxB.getCenter(centerB);
  float deltaCenterX = centerA.x - centerB.x;
  float deltaCenterY = centerA.y - centerB.y;
  float y = ((boxB.halfHeight + boxA.halfHeight) - Math.abs(deltaCenterY));
  float x = ((boxB.halfWidth + boxA.halfWidth) - Math.abs(deltaCenterX));
  float absX = Math.abs(x);
  float absY = Math.abs(y);
  if (absX > absY) out.y = absY * Math.signum(deltaCenterY);
  else out.x = absX * Math.signum(deltaCenterX);
 }

 private void handleBodySolid(Actor actorBody)
 {
  // logger.info("handleBodySolid");

  DrawingComponent drawingComponent = DrawingComponent.get(actorBody);
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
   float signum = Math.signum(deltaCenterY);

   if (Math.abs(vector2.y) < absY)
    vector2.y = absY * signum;
   //physics.position.y += absY * signum;
   //physics.position.y = signum > 0 ? boxB.getTop() : boxB.y - boxA.height;

   //boxA.setPosition( physics.position );

   //float positionY = signum > 0 ? boxB.getTop() : boxB.y - boxA.height;
   //if (vector2.y < positionY) vector2.y = positionY;

   //vector2.y = Math.max(vector2.y, valueY);

   // if (signum != Math.signum(physics.velocity.y)) physics.velocity.y = 0;

   //drawingComponent.extrapolationY = false;
   //drawingComponent.extrapolationOffNanoY = ApplicationLoop.instance.nextTickTime;
  }
  else
  {
   float signum = Math.signum(deltaCenterX);
   if (Math.abs(vector2.x) < absY)vector2.x = absX * signum;

    //physics.position.x += absX * signum;

   //physics.position.x = signum > 0 ? boxB.getRight() : boxB.x - boxA.width;
   //boxA.setPosition( physics.position );

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
  physics = BasePhysicsComponent.get(actorBody);
  physics.velocity.scl(0.5f, 0.5f);
 }
}
