package com.mygdx.game.ext.core.group;

import com.mygdx.game.ext.core.actor.Actor;

import java.util.ArrayList;
import java.util.Arrays;

public class Group extends ArrayList<Actor>
{
 public void addAll(Actor... actors) { this.addAll(Arrays.asList(actors)); }
 public void remAll(Actor... actors) { this.removeAll(Arrays.asList(actors)); }
}
