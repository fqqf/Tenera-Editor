package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.actors.Actor;

public class ExtrapolationDrawingComponent extends DrawingComponent
{

 private Field<Vector2> velocity;
 private float extr;


 @Override
 public void init(Actor<?> actor)
 {
  super.init(actor);
  if (actor.getField("velocity") == null) actor.addField("velocity", new Field<>(new Vector2()));
 }

 @Override @SuppressWarnings("unchecked")
 protected void loadFields(Actor<?> actor)
 {
  super.loadFields(actor);
  velocity = actor.getField("velocity");
 }

 @Override
 protected void calc()
 {
  extr = ApplicationLoop.instance.extrapolation;
  batch.draw(texture.get(), position.get().x+velocity.get().x*extr, position.get().y+velocity.get().y*extr, size.get().x, size.get().y);
 }
}
