package com.mygdx.game.ext.core.system;


import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.group.Group;

public abstract class System
{
 protected  Type type = Type.NONE;
 protected Actor actor;
 protected Group assignedActors = new Group();

 public void handle()
 {
  for (Actor actor: assignedActors)
  {
   this.actor = actor;
   loadFields();
   behave();
  }
 }

 protected void loadFields() {};
 protected void behave() {};

 public void addActor(Actor... actors) // Called by addSystem in actor +Due to be inherited
 {
  assignedActors.addAll(actors);
 }

 public enum Type
 {
  NONE,
  PHYSICS_SYSTEM,
  INPUT_SYSTEM,
  GRAPHICS_SYSTEM;
 }

 public Type getType() { if (type == Type.NONE) throw new NullPointerException("Please set type for your components!"); return type; }
}

/*
  for (Actor actor: assignedActors)
  {
   this.actor = actor;
   loadFields();
   behave();
  }
 */