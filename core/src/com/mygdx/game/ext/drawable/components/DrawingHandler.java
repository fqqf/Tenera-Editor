package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.Handler;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.components_type.IDrawCp;

public class DrawingHandler extends Handler<DrawingHandler> implements IDrawCp
{
 {
  type = ComponentType.GRAPHICS_COMPONENT;
 }

 protected SpriteBatch batch;

 protected Field<Vector2> position, size;
 protected Field<Texture> texture;

 @Override
 public void init(Actor<?> actor)
 {
  batch = Monitor.instance.getBatch();
  // TODO:  Use method for this a-la: initField(String name, new Vector2());
  if (actor.getField("position") == null) actor.addField("position", new Field<>(new Vector2()));
  if (actor.getField("size") == null) actor.addField("size", new Field<>(new Vector2()));
 }

 @Override
 public void behave(Actor<?> actor)
 {
  loadFields(actor); // Need separation in 2 methods because of a possible child components
  calc();
 }

 @SuppressWarnings("unchecked")
 protected void loadFields(Actor<?> actor)
 {
  // TODO: Make it look more simpler
  position = actor.getField("position");
  size = actor.getField("size");
  texture = actor.getField("texture");
 }

 protected void calc()
 {
  // TODO: Also try to make it more simpler, remove get();
  batch.draw(texture.get(), position.get().x, position.get().y, size.get().x, size.get().y);
 }
}
