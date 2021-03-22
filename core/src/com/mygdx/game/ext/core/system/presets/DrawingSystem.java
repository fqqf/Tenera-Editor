package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Rect;
import com.dongbat.jbump.World;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.system.System;
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
 public TreeMap<Integer, Layer> layers = new TreeMap<>();

 public void handle()
 {
  // logger.info("Drawing System");
  batch.begin();

  layers.forEach((key, layer) -> layer.forEach((this::calc)));
 // layers.forEach((key, layers) -> showWorldRects(layers, CollisionSystem.world));// debug

  batch.end();
 }

 private void iterateLayer(Layer layer)
 {
  batch.setProjectionMatrix(layer.getCoordinateGrid().camera.combined);
  layer.forEach(this::calc);
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
  texture = drawingComponent.texture;

  if (drawingComponent.useExtrapolation)
  {
   PhysicsComponent physicsComponent = PhysicsComponent.get(actor);
   position = physicsComponent.position;
   velocity = physicsComponent.velocity;
   size = physicsComponent.size;
  } else
  {
   BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(actor);
   position = bodyPropertiesComponent.position;
   size = bodyPropertiesComponent.size;
  }

 }

 protected void behave()
 {
  if (drawingComponent.useExtrapolation) // TODO: Упростить
  {
   float valueX = position.x, valueY = position.y;

   if (drawingComponent.extrapolationX) valueX += velocity.x * ApplicationLoop.instance.extrapolation;
   else if (drawingComponent.extrapolationOffNanoX < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;

   if (drawingComponent.extrapolationY) valueY += velocity.y * ApplicationLoop.instance.extrapolation;
   else if (drawingComponent.extrapolationOffNanoY < ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;

   batch.draw(texture, valueX, valueY, size.x, size.y);
  }
  else
  {
   batch.draw(texture, position.x, position.y, size.x, size.y);
  }


  //drawActorBox();
 }

 private void drawActorBox()
 {
  final PhysicsComponent physicsComponent = PhysicsComponent.get(actor);
  shapeDrawer.setColor(Color.GRAY);
  shapeDrawer.setDefaultLineWidth(0.02f);
  shapeDrawer.rectangle(physicsComponent.position.x, physicsComponent.position.y, physicsComponent.size.x, physicsComponent.size.y);
 }

 private void showWorldRects(Array<Actor> array, World<Actor> world)
 {
  for (Actor actor1 : array)
  {
   if (!CollisionComponent.has(actor1))continue;
   Item item = CollisionComponent.get(actor1).item;
   if (world.hasItem(item))
   {
    DrawingComponent drawingComponent = DrawingComponent.get(actor1);
    shapeDrawer.setColor(drawingComponent.debugCollisionColor);
    drawingComponent.debugCollisionColor = Color.PINK;
    shapeDrawer.setDefaultLineWidth(0.03f);
    Rect rect = world.getRect(item);
    shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
   }
  }
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
