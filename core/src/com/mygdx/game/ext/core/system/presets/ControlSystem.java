package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.components.Component;

public class ControlSystem extends System
{
 public ControlSystem()
 {
  type = Type.INPUT_SYSTEM;
 }

 private Vector2 position, velocity;

 @Override
 protected void loadFields()
 {
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);

  position = basePhysicsComponent.position;
  velocity = basePhysicsComponent.velocity;
 }

 @Override
 protected void behave()
 {
  if (Gdx.input.isKeyPressed(Input.Keys.W)) velocity.add(0,0.02f);
  if (Gdx.input.isKeyPressed(Input.Keys.A)) velocity.add(-0.02f, 0);
  if (Gdx.input.isKeyPressed(Input.Keys.S)) velocity.add(0,-0.02f);
  if (Gdx.input.isKeyPressed(Input.Keys.D)) velocity.add(0.02f, 0);
 }
}
