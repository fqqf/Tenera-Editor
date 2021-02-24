package com.mygdx.game.ext.drawable.actors;

import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.components.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Actor
{
 public void draw(float ext)
 {
  for (Component<?> component : components)
   if (component.getType() == Component.GRAPHICS_COMPONENT)
    component.handle(this);
 }

 public void act()
 {
  for (Component<?> component : components)
   if (component.getType() == Component.PHYSICS_COMPONENT)
    component.handle(this);
 }

 public void handleInput()
 {
  for (Component<?> component : components)
   if (component.getType() == Component.INPUT_COMPONENT)
    component.handle(this);
 }

 protected ArrayList<Component<?>> components = new ArrayList<>();

 public Actor addComp(Component<?>... components)
 {
  for (Component<?> component : components) component.init(this);
  this.components.addAll(Arrays.asList(components)); return this;
 }

 public Actor remComp(Component<?>... components) { this.components.removeAll(Arrays.asList(components)); return this; }  // TODO: Add names to components remComp("name")

 public HashMap<String, Field<?>> fields = new HashMap<>(); // fields from components


 private Field<?> field;

 public Field getField(String name, boolean log)
 {
  if (log && (field= fields.get(name))==null) Monitor.log.error("There is no field named \""+name+"\" in your actor");
  return fields.get(name);
 }

 public Field getField(String name)
 {
  return fields.get(name);
 }

 public Actor addField(String name, Field<?> field) { fields.put(name, field); return this; }  // TODO: addField must create field by itself, and get just simple object

 public void computeField(String name, Field<?> field) { if (getField(name) == null) addField(name, field); }

 // Used by Scene to determine call Actor methods or not
 public boolean doDrawing = true, doActing = true, doInputHandling = true;

 public void pause() { doDrawing = false;doActing = false;doInputHandling = false; }
 public void resume() { doDrawing = true;doActing = true;doInputHandling = true; }
}
