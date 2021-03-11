package com.mygdx.game.cur_test.Environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public abstract class EnvObject extends Actor
{
 protected DrawingComponent drawingComponent;
 protected CollisionComponent collisionComponent;
 public EnvObject(float x, float y, float width, float height)
 {
  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  drawingComponent = DrawingComponent.get(this);

  physicsComponent.position.set(x,y);
  physicsComponent.size.set(width, height);

  collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setPosition(x,y);
  collisionComponent.box.setSize(width, height);
  collisionComponent.box.setType(CollisionType.BODY);

  StickmanWorld.collisionManagmentSystem.addActor(this);
 }

 protected void setTexture(Texture texture)
 {
  drawingComponent.texture = texture;
 }
}
