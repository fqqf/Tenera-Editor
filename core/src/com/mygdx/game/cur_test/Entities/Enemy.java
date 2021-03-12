package com.mygdx.game.cur_test.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Enemy extends Actor
{
 public DrawingComponent drawingComponent;

 public Enemy(Vector2 position)
 {
  drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = new Texture("test7/enemy.png");
  drawingComponent.useExtrapolation = true;

  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  physicsComponent.position.set(position);
  physicsComponent.size.set(3,6);

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.BODY);
  collisionComponent.box.setPosition(position.x,position.y);
  collisionComponent.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  StickmanWorld.collisionManagmentSystem.addActor(this);
  StickmanWorld.physicsSystem.addActor(this);
  StickmanWorld.simpleBehaviourSystem.addActor(this);
 }
}