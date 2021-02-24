package com.mygdx.game.ext.drawable.components.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.Field;

public class ExtrapolationDrawingComponent extends DrawingComponent
{
 private Field<Vector2> velocity;
 private float extr;

 @Override
 public void init(Actor actor)
 {
  super.init(actor);
  actor.computeField("velocity", new Field<>(new Vector2()));
 }

 @Override
 protected void loadFields()
 {
  super.loadFields();
  velocity = actor.getField("velocity");
 }

 @Override
 protected void behave()
 {
  extr = ApplicationLoop.instance.extrapolation;
  batch.draw(texture.get(), position.get().x+velocity.get().x*extr, position.get().y+velocity.get().y*extr, size.get().x, size.get().y);
 }
}
