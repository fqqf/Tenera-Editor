package com.mygdx.game.ext.core.component;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.ext.core.actor.Actor;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public abstract class Component
{
 protected static Logger logger = new Logger("COMPONENT");
 protected Actor actor;
 //protected int type;
 protected  Type type = Type.NONE;

 public void init(Actor actor)
 {
  this.actor = actor;
  initFields();
 }

 public void handle(Actor actor)
 {
  this.actor = actor;
  loadFields();
  behave();
 }

 protected abstract void initFields();
 protected abstract void loadFields(); // TODO : Use annotation override @SupressWarnings("unchecked")
 protected abstract void behave(); // TODO : Use getV2 implementation in hard-coded components

 //public static final int PHYSICS_COMPONENT = 1;
 //public static final int INPUT_COMPONENT = 2;
 //public static final int GRAPHICS_COMPONENT = 3;

 public enum Type
 {
  NONE,
  PHYSICS_COMPONENT,
  INPUT_COMPONENT,
  GRAPHICS_COMPONENT;
 }

 //public int getType() { if (type == 0) throw new NullPointerException("Please set type for your components!"); return type; }
 public Type getType() { if (type == Type.NONE) throw new NullPointerException("Please set type for your components!"); return type; }
}
