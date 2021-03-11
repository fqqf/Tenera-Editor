package com.mygdx.game.ext.core.system;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;


public abstract class System
{
 public static final Logger logger = new Logger("SYSTEM", Logger.INFO);

 public int priority = 0;
 protected  Type type = Type.NONE;
 protected Actor actor;
 protected Group assignedActors = new Group();

 SpriteBatch batch;

 public void handle() //   layers.forEach((key,layer) -> layer.forEach((actor -> actor.draw(extrapolation))));
 {
   for (Actor actor : assignedActors)
   {
    this.actor = actor;
    loadFields();
    behave();
   }
 }

 protected void loadFields() {};
 protected void behave() {};

 public enum Type
 {
  NONE,
  PHYSICS_SYSTEM,
  RENDER_SYSTEM;
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