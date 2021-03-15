package com.mygdx.game.ext.core.components;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.lang.invoke.MethodHandles;

public abstract class Component<T extends Component<T>>
{
 protected final Actor actor;

 public Component(Actor actor)
 {
  this.actor = actor;
 }
}
