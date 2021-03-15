package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_test.Systems;
import com.mygdx.game.new_test.worlds.Darkness;

public abstract class Static extends Actor
{
 public Static(float x, float y, float width, float height, TextureAtlas.AtlasRegion texture)
 {
  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  CollisionComponent collisionComponent;

  physicsComponent.position.set(x,y);
  physicsComponent.size.set(width, height);
  drawingComponent.atlasRegion = texture;

  collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setPosition(x,y);
  collisionComponent.box.setSize(width, height);
  collisionComponent.box.setType(CollisionType.SOLID);

  Systems.collisionManagmentSystem.addActor(this);
 }
}

