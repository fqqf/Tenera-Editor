package com.mygdx.game.ext.drawable.scenes.presets;

import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.groups.Group;
import com.mygdx.game.ext.drawable.scenes.Scene;

import java.util.TreeMap;


public class GroupLayerScene<T extends Scene<T>> extends Scene<T>
{
 protected TreeMap<Integer, Group> layers = new TreeMap<>();

 public GroupLayerScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  super.iterDraw(extrapolation);

  batch.begin();
  layers.forEach((key,layer) -> layer.forEach((actor -> actor.draw(extrapolation))));
  batch.end();
 }
}
