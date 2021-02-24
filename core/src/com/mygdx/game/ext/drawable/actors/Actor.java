package com.mygdx.game.ext.drawable.actors;

import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.Component;
import com.mygdx.game.ext.drawable.components.ComponentType;
import com.mygdx.game.ext.drawable.components.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class Actor<T extends Actor<T>>
{
 public void draw(float ext)
 {
  for (Component<?> component : components)
   if (component.getType() == ComponentType.GRAPHICS_COMPONENT)
    component.behave(this);
 }

 public void act()
 {
  for (Component<?> component : components)
   if (component.getType() == ComponentType.PHYSICS_COMPONENT)
    component.behave(this);
 }

 public void handleInput()
 {
  for (Component<?> component : components)
   if (component.getType() == ComponentType.INPUT_COMPONENT)
    component.behave(this);
 }

 // TODO: 3 components lists
 protected ArrayList<Component<?>> components = new ArrayList<>();

 @SuppressWarnings("unchecked")
 public T addComp(Component<?>... components)
 {
  for (Component<?> component : components) component.init(this);
  this.components.addAll(Arrays.asList(components)); return (T) this;
 }

 @SuppressWarnings("unchecked")
 public T remComp(Component<?>... components)
 { this.components.removeAll(Arrays.asList(components)); return (T) this; }  // TODO: Add names to components remComp("name")

 public HashMap<String, Field<?>> componentsFields = new HashMap<>();;


 private Field field;

 public Field getField(String name, boolean log)
 {
  if (log && (field=componentsFields.get(name))==null) Monitor.log.error("There is no field named \""+name+"\" in your actor");
  return componentsFields.get(name);
 }

 public Field getField(String name)
 {
  return componentsFields.get(name);
 }



 // TODO: addField must create field by itself, and get just simple object
 public Actor<T> addField(String name, Field<?> field)
 {
  componentsFields.put(name, field);
  return (T) this;
 }

 // Used by Scene to determine call Actor methods or not
 public boolean doDrawing = true, doActing = true, doInputHandling = true;

 public void pause() { doDrawing = false;doActing = false;doInputHandling = false; }
 public void resume() { doDrawing = true;doActing = true;doInputHandling = true; }
}
