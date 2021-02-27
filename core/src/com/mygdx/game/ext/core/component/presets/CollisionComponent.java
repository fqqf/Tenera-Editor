package com.mygdx.game.ext.core.component.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.drawing.view.Monitor;

public class CollisionComponent extends Component
{
 {
  type = PHYSICS_COMPONENT;
 }

 protected Field<Vector2> boundingBoxPosition, boundingBoxSize;

 @Override
 protected void initFields()
 {
  actor.computeField("b-position", new Field<>(new Vector2()));
  actor.computeField("b-size", new Field<>(new Vector2()));
 }

 @Override
 protected void loadFields()
 {
  boundingBoxPosition = actor.getField("b-position");
  boundingBoxSize = actor.getField("b-size");
 }

 protected void behave()
 {
  // CHECK FOR COLLISIONS
 }
}
