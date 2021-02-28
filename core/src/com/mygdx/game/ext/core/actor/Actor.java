package com.mygdx.game.ext.core.actor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.interfaces.Func;

import java.util.HashMap;

public abstract class Actor
{
 public void draw(float ext) { handleComponents( 0, drawEndIndex); }
 public void act() { handleComponents(drawEndIndex, actEndIndex); }
 public void handleInput() { handleComponents(actEndIndex, inputEndIndex ); }

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

 //public void computeField(String name, Field<?> field) { if (getField(name) == null) addField(name, field); }
 public void computeField(String name, Func<Field<?>> supply) { if (getField(name) == null) addField(name, supply.invoke() ); }

 public boolean doDrawing = true, doActing = true, doInputHandling = true;  // Used by Scene to determine call Actor methods or not

 public void pause() { doDrawing = false;doActing = false;doInputHandling = false; }
 public void resume() { doDrawing = true;doActing = true;doInputHandling = true; }

 private int drawEndIndex, actEndIndex, inputEndIndex;
 private int getComponentIndex( final int componentType)
 { return componentType == Component.GRAPHICS_COMPONENT ? 0 : componentType == Component.PHYSICS_COMPONENT ? drawEndIndex : actEndIndex; }

 private void updateIndexOnChange(final int insertType, int value )
 {
  if ( insertType == Component.GRAPHICS_COMPONENT ) { drawEndIndex+=value; actEndIndex+=value;}
  else if (insertType == Component.PHYSICS_COMPONENT) actEndIndex+=value; inputEndIndex = components.size-1;
 }

// private void updateIndexAfterRemoveComponentType( final int removeType )
// {
//  if ( removeType == Component.GRAPHICS_COMPONENT ) {
//   drawEndIndex--; actEndIndex--;}
//  else if ( removeType == Component.PHYSICS_COMPONENT) actEndIndex--; inputEndIndex = components.size-1;
// }

 private void handleComponents(final int startIndewx, final int endIndex)
 { for (int i = startIndewx; i < endIndex; i++) components.get(i).handle(this); }

 protected Array<Component> components = new Array<>();

 public Actor addComp(Component... components)
 {
  for (Component component : components) addComp(component);
  return this;
 }

 private void addComp(Component component)
 {
  component.init( this );
  int index = getComponentIndex( component.getType() );
  components.insert( index, component );
  updateIndexOnChange(component.getType(), 1);
 }

 public Actor remComp(Component... components)
 { this.components.forEach(this::remComp); return this; }  // TODO: Add names to components remComp("name")

 private void remComp(Component component)
 { if (components.removeValue(component, true)) updateIndexOnChange(component.getType(), -1); }
}
