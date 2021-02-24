package com.mygdx.game.ext.drawable.actors;

import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.Handler;
import com.mygdx.game.ext.drawable.components.Field;
import com.mygdx.game.ext.drawable.components.components_type.IActCp;
import com.mygdx.game.ext.drawable.components.components_type.IDrawCp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class Actor<T extends Actor<T>>
{
 public void draw(float ext)
 {
  drawHandlers.forEach(handler -> handler.behave( this ));
 }

 public void act()
 {
  actHandlers.forEach(handler -> handler.behave(this));
 }

 public void handleInput()
 {
  inputHandlers.forEach(handler -> handler.behave(this));
 }

 // TODO: 3 components lists
 protected ArrayList<Handler<?>> drawHandlers = new ArrayList<>();
 protected ArrayList<Handler<?>> actHandlers = new ArrayList<>();
 protected ArrayList<Handler<?>> inputHandlers = new ArrayList<>();

 protected Collection<Handler<?>> getCollectionFor(Class<? extends Handler> type)
 {
  if ( IDrawCp.class.isAssignableFrom( type ) ) return drawHandlers;
  else if ( IActCp.class.isAssignableFrom( type ) ) return actHandlers;
  else return inputHandlers;
 }
 protected Collection<Handler<?>> getCollectionFor(Handler<?> handler)
 { return getCollectionFor( handler.getClass() ); }

 @SuppressWarnings("unchecked")
 public T addHandler(Handler<?>... handlers)
 {
  for (Handler<?> handler : handlers)
  {
   handler.init( this );
   getCollectionFor( handler ).add(handler);
  }
  return (T) this;
 }

 @SuppressWarnings("unchecked")
 public T remHandler(Handler<?>... handlers)
 {
  for (Handler<?> handler : handlers) getCollectionFor(handler.getClass()).remove(handler);
  return (T) this;
 }
 public T remHandler(Handler<?> handler)
 {
  getCollectionFor(handler).remove(handler);
  return (T) this;
 }

 // TODO: Add names to components remComp("name")

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
