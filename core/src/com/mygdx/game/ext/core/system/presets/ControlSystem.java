package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.system.System;

public class ControlSystem extends System
{
 public ControlSystem()
 {
  type = Type.RENDER_SYSTEM;
 }

 private Vector2 position, velocity, speed;

 @Override
 protected void loadFields()
 {
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);

  position = basePhysicsComponent.position;
  velocity = basePhysicsComponent.velocity;
  speed = basePhysicsComponent.speed;
 }

 @Override
 protected void behave()
 {
  // logger.info("Control System");

  if (Gdx.input.isKeyPressed(Input.Keys.W)) speed.y =  0.01f;
  if (Gdx.input.isKeyPressed(Input.Keys.S)) speed.y = -0.01f;
  if (Gdx.input.isKeyPressed(Input.Keys.A)) speed.x = -0.01f;
  if (Gdx.input.isKeyPressed(Input.Keys.D)) speed.x =  0.01f;

  if (!speed.isZero())
  {
   velocity.add(speed);
   speed.setZero();
  }

 }
}
