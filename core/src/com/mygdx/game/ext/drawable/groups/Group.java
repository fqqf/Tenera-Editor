package com.mygdx.game.ext.drawable.groups;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.drawable.actors.Actor;

import java.util.ArrayList;
import java.util.Arrays;

public class Group extends Array<Actor<?>>
{
 public Group() { super(false, 2);}
 public Group(int capacity) { super(false, capacity );}
 //public void addAll(Actor<?>... actors) { super.addAll( actors ); }
 public void remAll(Actor<?>... actors) { this.removeAll( Array.with( actors ) , true ); }
}
