package com.mygdx.game.ext.core.components;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.lang.invoke.MethodHandles;

public abstract class Component<T extends Component<T>>
{
 private final Actor actor;
 private ComputeableHashMap<T> motherListPointer; // Must be pointing on static mother list, containing <Actor, Component>

 public Component(Actor actor)
 {
  this.actor = actor;

  if (motherListPointer == null) throw new NullPointerException("" +
    "Set pointer of " +
    "[" + MethodHandles.lookup().lookupClass().asSubclass(this.getClass()).getName() + "]" +
    " to motherlist first!");

  motherListPointer.compute(actor, () -> (T)this);
 }
}
