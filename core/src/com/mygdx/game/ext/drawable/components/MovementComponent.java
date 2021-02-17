package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Actor;
import com.mygdx.game.ext.drawable.Component;

public class MovementComponent extends Component<MovementComponent>
{
 public ComponentField<Vector2> speed;
 public Vector2 acceleration;

 public MovementComponent()
 {
  type = ComponentType.PHYSICS_COMPONENT; // todo: remove comptype class
 }

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.componentValues.get("speed") == null)
  {
   actor.componentValues.put("speed", new ComponentField<>(Vector2::new));
  }

  speed = actor.componentValues.get("speed");
  speed.get().x = 0.1f;
 }

 @Override @SuppressWarnings("unchecked")
 public void behave(Actor<?> actor)
 {
  speed = actor.componentValues.get("speed");

  actor.position.add(speed.get());
 }
}
