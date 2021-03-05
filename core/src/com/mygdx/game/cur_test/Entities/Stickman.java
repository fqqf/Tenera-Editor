package com.mygdx.game.cur_test.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;

public class Stickman extends Actor
{
 public Stickman(Vector2 position)
 {
  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  CollisionComponent collisionComponent = CollisionComponent.get(this);

  drawingComponent.texture = new Texture("stickman.png");

  physicsComponent.position.set(position);
  physicsComponent.size.set(3,6);

  StickmanWorld.collisionManagmentSystem.addActor(this);
  StickmanWorld.controlSystem.addActor(this);
  StickmanWorld.physicsSystem.addActor(this);
  StickmanWorld.extrapolationDrawingSystem.addActor(this);
 }
}
