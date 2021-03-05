package com.mygdx.game.ext.core.actor;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.component.FieldType;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.actor.interfaces.Func;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Actor
{
 public void draw(float ext)
 {
  callComponents(0, actStartIndex);
 }

 public void act()
 {
  callComponents(actStartIndex, inputStartIndex);
 }

 public void handleInput()
 {
  callComponents(inputStartIndex, components.size);
 }

 public HashMap<String, Field<? extends Object>> fields = new HashMap<>(); // fields from components

 @SuppressWarnings("unchecked")
 public Field getField(String name, boolean log)
 {
  Field<?> field;
  if (log && (field = fields.get(name)) == null)
   Monitor.log.error("There is no field named \"" + name + "\" in your actor");
  return fields.get(name);
 }

 public Field getField(String name)
 {
  return fields.get(name);
 }

 public Actor addField(String name, Field<?> field)
 {
  fields.put(name, field);
  return this;
 }

 //public void computeField(String name, Field<?> field) { if (getField(name) == null) addField(name, field); }
 public void computeField(String name, Func<Field<?>> supply)
 {
  if (getField(name) == null) addField(name, supply.invoke());
 }

 public boolean doDrawing = true, doActing = true, doInputHandling = true;  // Used by Scene to determine call Actor methods or not

 public void pause()
 {
  doDrawing = false;
  doActing = false;
  doInputHandling = false;
 }

 public void resume()
 {
  doDrawing = true;
  doActing = true;
  doInputHandling = true;
 }

 private int actStartIndex, inputStartIndex;

 private int getComponentIndex(final Component.Type componentType)
 {
  return componentType == Component.Type.GRAPHICS_COMPONENT ? 0 : componentType == Component.Type.PHYSICS_COMPONENT ? actStartIndex : inputStartIndex;
 }

 private void updateIndexOnChange(final Component.Type insertType, int value)
 {
  if (insertType == Component.Type.GRAPHICS_COMPONENT)
  {
   actStartIndex += value;
   inputStartIndex += value;
  } else if (insertType == Component.Type.PHYSICS_COMPONENT) inputStartIndex += value;
 }

 private void callComponents(final int startIndewx, final int endIndex)
 {
  for (int i = startIndewx; i < endIndex; i++) components.get(i).handle(this);
 }

 private final Array<Component> components = new Array<>();

 public Actor addComp(Component... components)
 {
  for (Component component : components) addComp(component);
  return this;
 }

 private void addComp(Component component)
 {
  component.init(this);
  int index = getComponentIndex(component.getType());
  components.insert(index, component);
  updateIndexOnChange(component.getType(), 1);
 }

 public Actor remComp(Component... components)
 {
  for (Component component : components) remComp(component);
  return this;
 }

 private void remComp(Component component)
 {
  if (components.removeValue(component, true)) updateIndexOnChange(component.getType(), -1);
 }
}
