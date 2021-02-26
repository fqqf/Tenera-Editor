package com.mygdx.game.ext.drawable.actors;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.components.Field;

import java.util.Arrays;
import java.util.HashMap;

public abstract class Actor
{
 private int updateStartIndex, inputStartIndex, inputEndIndex;
 private int getComponentIndex( final int componentType) { return componentType == Component.GRAPHICS_COMPONENT ? 0 : componentType == Component.PHYSICS_COMPONENT ? updateStartIndex : inputStartIndex; }
 protected void updateIndexAfterInsertComponentType( final int insertType )
 {
  if ( insertType == Component.GRAPHICS_COMPONENT ) {updateStartIndex++; inputStartIndex++;}
  else if (insertType == Component.PHYSICS_COMPONENT) inputStartIndex++; inputEndIndex = components.size-1;
 }
 protected void updateIndexAfterRemoveComponentType( final int removeType )
 {
  if ( removeType == Component.GRAPHICS_COMPONENT ) {updateStartIndex--; inputStartIndex--;}
  else if ( removeType == Component.PHYSICS_COMPONENT) inputStartIndex--; inputEndIndex = components.size-1;
 }

 public void draw(float ext) { handleComponents( 0, updateStartIndex ); }
 public void act() { handleComponents( updateStartIndex, inputStartIndex ); }
 public void handleInput() { handleComponents( inputStartIndex, inputEndIndex ); }

 private void handleComponents(final int startIndewx, final int endIndex){ for (int i = startIndewx; i < endIndex; i++) components.get( i ).handle( this ); }

 protected Array<Component<?>> components = new Array<>();

 public Actor addComp(Component<?>... components)
 {
  for (Component<?> component : components) addComp( component );
  return this;
 }
 public Actor addComp(Component<?> component)
 {
  component.init( this );
  int index = getComponentIndex( component.getType() );
  components.insert( index, component );
  updateIndexAfterInsertComponentType( component.getType() );
  return this;
 }

 public Actor remComp(Component<?>... components) { this.components.forEach( this::remComp ); return this; }  // TODO: Add names to components remComp("name")
 public Actor remComp(Component<?> component)
 {
  if ( components.removeValue( component, true ) )updateIndexAfterRemoveComponentType( component.getType() );
  return this;
 }

 public HashMap<String, Field<?>> fields = new HashMap<>(); // fields from components


 public Field getField(String name, boolean log)
 {
  Field<?> field;
  if (log && (field = fields.get(name))==null) Monitor.log.error("There is no field named \""+name+"\" in your actor");
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
