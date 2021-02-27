package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.Scene;

import java.util.TreeMap;


public class GroupLayerScene extends Scene
{
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

 TreeMap<Integer, Group> layers = new TreeMap<>();
}
