package com.mygdx.game.ext.drawable;

import com.mygdx.game.ext.drawable.Actor;

public abstract class Component<T, T2>
{
 T t;

 public Component(Actor<?> actor)
 {
  System.out.println(actor);
 }

 public abstract void behave();
}
