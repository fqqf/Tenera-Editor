package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.TreeMap;

public class DrawingSystem extends System
{
 private final ShapeDrawer shapeDrawer;
 public DrawingSystem()
 {
  batch = Monitor.instance.getBatch();
  type = Type.RENDER_SYSTEM;
  priority = 100;
  shapeDrawer = new ShapeDrawer(Monitor.instance.getBatch(), new TextureRegion(new Texture(Gdx.files.internal("white.jpg"))));
 }

 protected SpriteBatch batch;
 protected TextureAtlas.AtlasRegion texture;
 protected Vector2 position, size, velocity;
 private DrawingComponent drawingComponent;

 public TreeMap<Integer, Group> layers = new TreeMap<>();
 private float deltaAccum;
 public void handle()
 {
  // logger.info("Drawing System");
  deltaAccum += Gdx.graphics.getDeltaTime();
  batch.begin();
  layers.forEach((key,layer) -> layer.forEach((this::calc)));
  batch.end();
 }

 private void calc(Actor actor)
 {
  this.actor = actor;
  loadFields();
  behave();
 }

 protected void loadFields()
 {
  drawingComponent = DrawingComponent.get(actor);
  texture = drawingComponent.atlasRegion;

  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor); // TODO: separate in properties and texture;;
  position = basePhysicsComponent.position; size = basePhysicsComponent.size;
  velocity = BasePhysicsComponent.get(actor).velocity;
 }

 protected void behave()
 {
  if (drawingComponent.useExtrapolation)
  {
   float extr = ApplicationLoop.instance.extrapolation;
   float valueX,valueY;
   if (drawingComponent.extrapolationX) valueX = velocity.x*extr;
   else
   {
    valueX = 0;
    if (drawingComponent.extrapolationOffNanoX < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;
   }
   if (drawingComponent.extrapolationY) valueY = velocity.y*extr;
   else
   {
    valueY = 0;
    if (drawingComponent.extrapolationOffNanoY < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;
   }
   float x = position.x + valueX;float y = position.y + valueY;

   if (drawingComponent.showStatic) batch.draw(texture, x, y, size.x, size.y);
   for (int i = 0; i < drawingComponent.animations.size; i++)
   {
    AnimationData animationData = drawingComponent.animations.get(i);
    batch.draw(animationData.getKeyFrame(deltaAccum), x + animationData.offsetX, y + animationData.offsetY, animationData.width, animationData.height);
   }
  }
  else
   {
    if (drawingComponent.showStatic) batch.draw(texture, position.x, position.y, size.x, size.y);
    for (int i = 0; i < drawingComponent.animations.size; i++)
    {
     AnimationData animationData = drawingComponent.animations.get(i);
     batch.draw( animationData.getKeyFrame(deltaAccum), position.x + animationData.offsetX, position.y + animationData.offsetY, animationData.width, animationData.height);
    }
   }



  // drawActorBox();
  // drawCollisionBox();
 }
 private void drawCollisionBox()
 {
  final CollisionComponent collisionComponent = CollisionComponent.get(actor);
  final BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);

  shapeDrawer.setColor(basePhysicsComponent.color);
  basePhysicsComponent.color = Color.GREEN;
  shapeDrawer.setDefaultLineWidth(0.02f);
  BoundingBox box = collisionComponent.box;
  shapeDrawer.rectangle(box.x, box.y, box.width, box.height);
 }
 private void drawActorBox()
 {
  final BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);
  shapeDrawer.setColor(Color.GRAY);
  shapeDrawer.setDefaultLineWidth(0.02f);
  shapeDrawer.rectangle(basePhysicsComponent.position.x, basePhysicsComponent.position.y, basePhysicsComponent.size.x, basePhysicsComponent.size.y);
 }
}

/* COMMENT SECTION */

//   float valueX,valueY;
//   if (drawingComponent.extrapolationX) valueX = velocity.x*extr;
//   else
//   {
//    color = Color.RED;
//    valueX = 0;
//    if (drawingComponent.extrapolationOffNanoX < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;
//   }
//   if (drawingComponent.extrapolationY) valueY = velocity.y*extr;
//   else
//   {
//    color = Color.RED;
//    valueY = 0;
//    if (drawingComponent.extrapolationOffNanoY < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;
//   }
