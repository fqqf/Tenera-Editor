package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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

   //if (actor.getField("position") == null) actor.addField("position", new Field<>(new Vector2(0,0)));
   //if (actor.getField("size") == null) actor.addField("size", new Field<>(new Vector2(0,0)));

   Field<Vector2> position = actor.getField("position"), size = actor.getField("size");

   actor.computeField( "AABBRectangle",()-> new Field<>( new Rectangle(position.get().x,position.get().y, size.get().x,size.get().y )));

   //if (actor.getField("AABBposition") == null) actor.addField("position", new Field<>(new Vector2().set(size.get())));
   //if (actor.getField("AABBsize") == null) actor.addField("size", new Field<>(new Vector2().set(position.get())));
  }
 }
}
