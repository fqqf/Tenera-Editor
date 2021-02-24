package com.mygdx.game.ext.drawable;


import com.mygdx.game.ext.drawable.actors.Actor;

public abstract class Handler<T>
{
 // TODO : replace type and ComponentType, so Component can become interface
 @Deprecated
 protected int type;
 public final String name = getClass().getSimpleName();
 public abstract void init(Actor<?> actor);
 public abstract void behave(Actor<?> actor); // TODO : Use annotation override for loadFields()' and calc();

 @Deprecated
 public int getType() { return type; }
}
