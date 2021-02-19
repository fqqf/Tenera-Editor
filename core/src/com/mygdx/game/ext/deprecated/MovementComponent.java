package com.mygdx.game.ext.deprecated;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Component;
import com.mygdx.game.ext.drawable.components.ComponentType;
import com.mygdx.game.ext.drawable.components.Field;
/*
public class MovementComponent extends Component<MovementComponent>
{
 public Field<Vector2> speed;
 public Vector2 acceleration;

 public MovementComponent()
 {
  type = ComponentType.PHYSICS_COMPONENT; // todo: remove comptype class
 }

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.actorFields.get("speed") == null)
  {
   actor.actorFields.put("speed", new Field<>(Vector2::new));
  }

  speed = actor.actorFields.get("speed");
  speed.get().x = 0.1f;
 }

 @Override @SuppressWarnings("unchecked")
 public void behave(Actor<?> actor)
 {
  speed = actor.actorFields.get("speed");

  actor.position.add(speed.get());
 }
}
*/