package com.mygdx.game.ext.core.component.presets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.Field;

public class DrawingComponent extends Component
{
 {
  type = GRAPHICS_COMPONENT;
 }

 protected SpriteBatch batch;

 protected Field<Vector2> position, size;
 protected Field<Texture> texture;

 @Override
 protected void initFields()
 {
  batch = Monitor.instance.getBatch();

  actor.computeField("position", ()->new Field<>(new Vector2()) );
  actor.computeField("size", ()->new Field<>(new Vector2()));
 }

 @Override
 protected void loadFields()
 {
  position = actor.getField("position");
  size = actor.getField("size");
  texture = actor.getField("texture");

  if (texture == null) {throw new NullPointerException("Calling drawing component on null-texture actor. Please call actor.addField((\"texture\"), new Field<>(new Texture()))"); }
 }

 protected void behave()
 {

  // TODO: Also try to make it more simpler, remove get();
  batch.draw(texture.get(), position.get().x, position.get().y, size.get().x, size.get().y);
 }
}
