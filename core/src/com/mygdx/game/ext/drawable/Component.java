package com.mygdx.game.ext.drawable;

import com.mygdx.game.ext.drawable.Actor;

public abstract class Component<T>
{
 // TODO : replace type and ComponentType, so Component can become interface
 protected int type;

 public abstract void init();
 public abstract void behave(Actor<?> actor);
}
