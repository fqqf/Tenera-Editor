package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.group.Group;

public class CollisionManager
{
 Group actors = new Group(); //managed by another class

 public void calc()
 {
  // go thru all actors and find collision
 }

 public void addActor(Actor... actors)
 {
  for (Actor actor: actors)
  {
   // TODO: replace compute, because even if actor has no this field it will create object
   actor.computeField( "position",()->new Field<>(new Vector2(0,0)) );
   actor.computeField( "size",()->new Field<>(new Vector2(0,0)) );

   Field<Vector2> position = actor.getField("position"), size = actor.getField("size");

   actor.computeField( "AABBposition",()->new Field<>(new Vector2(0,0)) );
   actor.computeField( "AABBsize",()->new Field<>(new Vector2(0,0)) );
  }
 }
 private final Rectangle rectangle1 = new Rectangle(), rectangle2 = new Rectangle();
 public void checkCollision(Actor actor1, Actor actor2)
 {
  setRectangleData(actor1, rectangle1);
  setRectangleData(actor2, rectangle2);
  if (rectangle1.overlaps(rectangle2))
  {

  }
 }
 private void handleCollision()
 {

 }
 private void setRectangleData(Actor actor, Rectangle rectangle)
 {
  Field<Vector2> position = actor.getField("position");
  Field<Vector2> size = actor.getField("size");
  rectangle.setPosition(position.get());
  rectangle.setSize(size.get().y, size.get().y);
 }

 private Vector2 getSpeed(Actor actor)
 {
  Field<Vector2> speedField = actor.getField("velocity");
  return speedField.get();
 }


}
