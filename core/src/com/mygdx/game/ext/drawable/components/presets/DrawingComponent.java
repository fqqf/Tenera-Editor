package com.mygdx.game.ext.drawable.components.presets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.Field;

public class DrawingComponent extends Component<DrawingComponent>
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

  actor.computeField("position", new Field<>(new Vector2()));
  actor.computeField("size", new Field<>(new Vector2()));
 }

 @Override
 protected void loadFields()
 {
  position = actor.getField("position");
  size = actor.getField("size");
  texture = actor.getField("texture");
 }

 protected void behave()
 {
  // TODO: Also try to make it more simpler, remove get();
  batch.draw(texture.get(), position.get().x, position.get().y, size.get().x, size.get().y);
 }
}