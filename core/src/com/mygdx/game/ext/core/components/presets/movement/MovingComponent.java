package com.mygdx.game.ext.core.components.presets.movement;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class MovingComponent extends Component
{
 private float speed = 0;
 private float acc = 0;

 public float getSpeed()
 {
  speed = 0;
  speed += acc;

  acc = 0;

  return speed;
 }

 public void move(float vel)
 {
  acc+=vel;
 }

 protected static final ComputeableHashMap<MovingComponent> childList = new ComputeableHashMap<>();

 public static MovingComponent get(Actor actor) { return childList.compute(actor, MovingComponent::new); }
}
