package com.mygdx.game.ext.drawable.scenes.presets;

import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.groups.Group;
import com.mygdx.game.ext.drawable.scenes.Scene;

import java.util.Arrays;
import java.util.TreeMap;

public class LayerScene extends Scene<LayerScene>
{
 protected TreeMap<Integer, Group> layers = new TreeMap<>();

 public LayerScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  layers.forEach((key,layer) -> layer.forEach((actor -> actor.draw(extrapolation))));
 }

 @Override
 protected LayerScene actor(Actor<?>... actors)
 {
  this.actors.addAll(Arrays.asList(actors)); return this;
 }
}
