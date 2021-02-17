package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Actor;
import com.mygdx.game.ext.drawable.Component;

public class MovementComponent extends Component<MovementComponent>
{
 public Vector2 speed;
 public Vector2 acceleration;

 public MovementComponent()
 {
  type = ComponentType.PHYSICS_COMPONENT; // todo: remove comptype class

  speed = new Vector2();
  acceleration = new Vector2();
 }

 @Override
 public void init()
 {
  speed.x = 0.1f;
 }

 @Override
 public void behave(Actor<?> actor)
 {
  speed =

  speed.add(acceleration);
  actor.position.add(speed);
 }
}
