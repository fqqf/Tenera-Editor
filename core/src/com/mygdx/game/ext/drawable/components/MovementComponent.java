package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.Actor;
import com.mygdx.game.ext.drawable.Component;

public class MovementComponent extends Component
{
 Actor actor;
 Vector2 position;

 public MovementComponent(Actor<?> actor)
 {
  super(actor);
 }


 @Override
 public void behave()
 {

 }
}
