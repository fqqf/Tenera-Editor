package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.System;

public class PhysicsSystem extends System
{
 private Vector2 position, velocity;
 private BoundingBox box;

 {
  type = Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
 }

 @Override
 protected void loadFields()
 {
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);
  CollisionComponent collisionComponent = CollisionComponent.get(actor);

  position = basePhysicsComponent.position;
  velocity = basePhysicsComponent.velocity;

  box = collisionComponent.box;
 }

 @Override
 protected void behave()
 {

  position.add(velocity);
  if (position.y>0) velocity.y-=0.05f; else { velocity.y = 0;position.y = 0; } // TODO: disable extrapolation in here
  if (Math.abs(velocity.x)>0.001f) velocity.x-=(velocity.x)/10;else velocity.x = 0;
  box.setPosition(position);
 }
}
