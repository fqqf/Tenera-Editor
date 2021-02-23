package com.mygdx.game.ext.drawable.groups;

import com.mygdx.game.ext.drawable.actors.Actor;

public class TreeMap extends java.util.TreeMap<Integer, Actor<?>>
{
 public TreeMap lay(Integer key, Actor<?> value)
 {
  return this;
 }
}
