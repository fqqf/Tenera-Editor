package com.mygdx.game.ext.drawable;


import com.mygdx.game.ext.drawable.actors.Actor;

public abstract class Component<T>
{
 // TODO : replace type and ComponentType, so Component can become interface
 protected int type;
 public abstract void init(Actor<?> actor);
 public abstract void behave(Actor<?> actor); // TODO : Use annotation override for loadFields()' and calc();

 public int getType() { return type; }
}
