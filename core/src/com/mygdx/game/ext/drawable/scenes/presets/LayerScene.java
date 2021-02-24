package com.mygdx.game.ext.drawable.scenes.presets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.utils.TreeMap;
import com.mygdx.game.ext.drawable.scenes.Scene;


public class LayerScene<T extends Scene<T>> extends Scene<T>
{
 protected TreeMap layers = new TreeMap();

 public LayerScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  super.iterDraw(extrapolation);

  batch.begin();
  layers.forEach((key, layer) -> layer.draw(extrapolation));
  batch.end();
 }

 @Override
 protected T addActor(Actor<?>... actors)
 {
  return super.addActor(actors);
 }

 @Override
 protected Actor<?>[] remActor(Actor<?>... actors)
 {
  return super.remActor(actors);
 }
}
