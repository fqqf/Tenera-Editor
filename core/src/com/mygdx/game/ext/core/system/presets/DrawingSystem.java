package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.System;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.TreeMap;

public class DrawingSystem extends System
{
 ShapeDrawer shapeDrawer;
 public DrawingSystem()
 {
  batch = Monitor.instance.getBatch();
  type = Type.GRAPHICS_SYSTEM;
  shapeDrawer = new ShapeDrawer(batch, new TextureRegion(new Texture(Gdx.files.internal("white.jpg"))));
 }

 protected SpriteBatch batch;
 protected Texture texture;
 protected Vector2 position, size, velocity;
 private DrawingComponent drawingComponent;

 public TreeMap<Integer, Group> layers = new TreeMap<>();

 public void handle()
 {

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
  texture = drawingComponent.texture;


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
    if (drawingComponent.extrapolationOffNanoX <= ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationX = true;
   }
   if (drawingComponent.extrapolationY) valueY = velocity.y*extr;
   else
   {
    valueY = 0;
    if (drawingComponent.extrapolationOffNanoY <= ApplicationLoop.instance.inGameTime) drawingComponent.extrapolationY = true;
   }
   batch.draw(texture, position.x+valueX, position.y+valueY, size.x, size.y);
  }
  else batch.draw(texture, position.x, position.y, size.x, size.y);

  shapeDrawer.setColor(Color.BLUE);
  shapeDrawer.setDefaultLineWidth(0.02f);
  shapeDrawer.rectangle( position.x, position.y, size.x, size.y);

 }
}
