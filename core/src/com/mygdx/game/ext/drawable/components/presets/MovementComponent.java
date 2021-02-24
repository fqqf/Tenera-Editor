package com.mygdx.game.ext.drawable.components.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.Field;

public class MovementComponent extends Component<MovementComponent>
{
 private Field<Vector2> position, velocity;

 {
  type = PHYSICS_COMPONENT;
 }

 @Override
 protected void initFields()
 {
  actor.computeField("velocity", new Field<>(new Vector2(0.15f, 0)));
  actor.computeField("position", new Field<>(new Vector2(0.15f, 0)));
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
  position.get().add(velocity.get());
 }
}
