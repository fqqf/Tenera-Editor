package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.System;

public class PhysicsSystem extends System
{
 private Vector2 position, velocity, speed;

 {
  type = Type.PHYSICS_SYSTEM; // PHYSICS_COMPONENT;
  priority = 1;
 }

 @Override
 protected void loadFields()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(actor);

  position = physicsComponent.position;
  velocity = physicsComponent.velocity;
  speed = physicsComponent.speed;
 }

 @Override
 protected void behave()
 {
  // logger.info("Physic System");

  //if (position.y>0) velocity.y-=0.02f;
  //else { velocity.y = 0; position.y = 0; } // TODO: disable extrapolation in here

 // if (Math.abs(velocity.x)>0.001f) velocity.x-=(velocity.x) / 10f;
 // else velocity.x = 0;

  //if (Math.abs(velocity.x) > 1) velocity.x = Math.signum(velocity.x) * 1;
  //if (Math.abs(velocity.y) > 1) velocity.y = Math.signum(velocity.y) * 1;

 // if (Math.abs(velocity.x)>0.001f) velocity.x*=0.8f;// else velocity.x = 0;
  //if (Math.abs(velocity.y)>0.001f) velocity.y*=0.8f;// else velocity.x = 0;

  if (velocity.y>-0.5f) velocity.y = velocity.y - 0.1f; else velocity.y = -0.5f;

  if (Math.abs(velocity.x)!=0 && Math.abs(velocity.x)>0.01f) velocity.x = velocity.x - velocity.x/Math.abs(velocity.x)*0.01f; else velocity.x = 0;

  position.add(velocity);

  // java.lang.System.out.println("Speed x="+velocity.x + ",y="+velocity.y);
  // velocity.setZero();
 }
}
