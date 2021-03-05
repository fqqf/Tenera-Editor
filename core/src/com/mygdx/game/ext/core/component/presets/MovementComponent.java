package com.mygdx.game.ext.core.component.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.additional.collisionSystem.BoundingBox;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;

public class MovementComponent extends Component
{
 private Field<Vector2> position, velocity;
 private Field<BoundingBox> box;

 {
  type = Type.PHYSICS_COMPONENT;// PHYSICS_COMPONENT;
 }

 @Override
 protected void initFields()
 {
  actor.computeField("velocity", ()->new Field<>(new Vector2(0, 0)));
  actor.computeField("position", ()->new Field<>(new Vector2(0, 0)));
 }

 @Override
 protected void loadFields()
 {
  position = actor.getField("position");
  velocity = actor.getField("velocity");
  box = actor.getField("box");
 }

 @Override
 protected void behave()
 {
  position.get().add(velocity.get());
  box.get().setPosition( position.get() );
  System.out.println("position: x=" + position.get().x + " y=" + position.get().y);
 }
}
