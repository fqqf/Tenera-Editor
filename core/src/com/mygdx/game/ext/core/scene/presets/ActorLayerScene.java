package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

import java.util.TreeMap;


public class ActorLayerScene extends Scene
{
 public ActorLayerScene(String name, ExtendCoordinateGrid field, float width, float height)
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

 TreeMap<Integer, Actor> layers = new TreeMap<>();
}
