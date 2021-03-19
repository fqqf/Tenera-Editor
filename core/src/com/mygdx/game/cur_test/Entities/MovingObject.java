package com.mygdx.game.cur_test.Entities;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class MovingObject extends Actor
{
 public MovingObject(Vector2 position, Vector2 size, TextureAtlas.AtlasRegion texture)
 {
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.atlasRegion = texture;
  drawingComponent.useExtrapolation = true;

  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  physicsComponent.position.set(position);
  physicsComponent.size.set(size);

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.BODY);
  collisionComponent.box.setPosition(position.x, position.y);
  collisionComponent.box.setSize(physicsComponent.size.x, physicsComponent.size.y);


  StickmanWorld.collisionSystem.addActor(this);
  StickmanWorld.physicsSystem.addActor(this);
 }
}
