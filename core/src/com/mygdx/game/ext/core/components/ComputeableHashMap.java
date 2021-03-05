package com.mygdx.game.ext.core.components;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.function.BiFunction;

public class ComputeableHashMap<T> extends HashMap<Actor, T>
{
 T t;

 public T compute(Actor actor, Func<T> supply)
 {
  t = this.get(actor);
  if (t == null) this.put(actor, supply.invoke());
  return t;
 }
}
