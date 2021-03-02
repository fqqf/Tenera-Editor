package com.mygdx.game.ext.core.component.presets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;

public class ControlComponent extends Component
{
 private Field<Vector2> position, velocity;

 // TODO: set INPUT_COMPONENT
 {
  type = Type.INPUT_COMPONENT;//INPUT_COMPONENT;//GRAPHICS_COMPONENT;
 }

 @Override
 protected void initFields()
 {
  actor.computeField("velocity", ()->new Field<>(new Vector2(0.15f, 0)));
  actor.computeField("position", ()->new Field<>(new Vector2(0.15f, 0)));
 }

 @Override
 protected void loadFields()
 {
  position = actor.getField("position");
  velocity = actor.getField("velocity");
 }

 @Override
 protected void behave()
 {
  if (Gdx.input.isKeyPressed(Input.Keys.W)) velocity.get().add(0,0.02f);
  if (Gdx.input.isKeyPressed(Input.Keys.A)) velocity.get().add(-0.02f, 0);
  if (Gdx.input.isKeyPressed(Input.Keys.S)) velocity.get().add(0,-0.02f);
  if (Gdx.input.isKeyPressed(Input.Keys.D)) velocity.get().add(0.02f, 0);
 }
}
