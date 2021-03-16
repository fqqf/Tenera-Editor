package com.mygdx.game.new_test.creatures;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_test.Systems;

public abstract class Moveable extends Actor
{
 public Moveable(float x, float y, float width, float height, TextureAtlas.AtlasRegion texture)
 {
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.atlasRegion = texture;
  drawingComponent.useExtrapolation = true;

  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(width, height);

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.BODY);
  collisionComponent.box.setPosition(x, y);
  collisionComponent.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.collisionManagmentSystem.addActor(this);
  Systems.physicsSystem.addActor(this);
 }
}
