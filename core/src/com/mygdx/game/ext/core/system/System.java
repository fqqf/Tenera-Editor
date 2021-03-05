package com.mygdx.game.ext.core.system;


import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.group.Group;


public abstract class System
{
 public static final Logger logger = new Logger("SYSTEM", Logger.INFO);

 protected  Type type = Type.NONE;
 protected Actor actor;
 protected Group assignedActors = new Group(); // TODO: ADD Priority

 public void handle() //   layers.forEach((key,layer) -> layer.forEach((actor -> actor.draw(extrapolation))));
 {
   for (Actor actor : assignedActors)
   {
    this.actor = actor;
    loadFields();
    behave();
   }
 }

 public void setGroup(Group group) // Нужно добавить интерфейс типа Stackable, где PriorityGroup extends Stackable и Group extends Stackable
 {
  assignedActors = group;
 }

 protected void loadFields() {};
 protected void behave() {};

 public enum Type
 {
  NONE,
  PHYSICS_SYSTEM,
  INPUT_SYSTEM,
  GRAPHICS_SYSTEM;
 }

 public Type getType() { if (type == Type.NONE) throw new NullPointerException("Please set type for your components!"); return type; }

 public void addActor(Actor... actors)
 {
  assignedActors.addAll(actors);
 }
}

/*
  for (Actor actor: assignedActors)
  {
   this.actor = actor;
   loadFields();
   behave();
  }
 */