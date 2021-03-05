package com.mygdx.game.ext.core.components;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.util.HashMap;

public class ComputeableHashMap<T> extends HashMap<Actor, T>
{
 T t;

 public T compute(Actor actor, Func<T> supply)
 {
  t = this.get(actor);
  if (t == null)
  {
   put(actor, supply.invoke());
   t = this.get(actor);
  }

  return t;
 }
}
