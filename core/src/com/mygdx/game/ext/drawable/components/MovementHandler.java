package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Handler;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.components_type.IActCp;

public class MovementHandler extends Handler<MovementHandler> implements IActCp
{
 private Field<Vector2> position, velocity;

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.getField("velocity") == null) actor.addField("velocity", new Field<>(new Vector2(0.15f,0)));
  if (actor.getField("position") == null) actor.addField("position", new Field<>(new Vector2()));
 }

 @Override
 public void behave(Actor<?> actor)
 {
  position = actor.getField("position");
  velocity = actor.getField("velocity");

  position.get().add(velocity.get());
 }
}
