package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.System;

import java.util.TreeMap;

public class DrawingSystem extends System
{
 public DrawingSystem()
 {
  batch = Monitor.instance.getBatch();
  type = Type.GRAPHICS_SYSTEM;
 }

 protected SpriteBatch batch;
 protected Texture texture;
 protected Vector2 position, size;

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
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor); // TODO: separate in properties and texture;;
  DrawingComponent drawingComponent = DrawingComponent.get(actor);

  texture = drawingComponent.texture;
  position = basePhysicsComponent.position; size = basePhysicsComponent.size;
 }

 protected void behave()
 {
  batch.draw(texture, position.x, position.y, size.x, size.y);
 }
}
