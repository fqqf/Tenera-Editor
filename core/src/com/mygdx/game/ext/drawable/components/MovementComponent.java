package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Actor;
import com.mygdx.game.ext.drawable.Component;

public class MovementComponent extends Component<MovementComponent>
{
 Vector2 speed;
 Vector2 acceleration;

 public MovementComponent()
 {
  speed = new Vector2();
  acceleration = new Vector2();
 }

 @Override
 public void init(Actor<?> actor)
 {
  super.init(actor);
  speed.x = 0.1f;
 }

 @Override
 public void behave()
 {
  speed.add(acceleration);
  actor.position.add(speed);
 }
}
