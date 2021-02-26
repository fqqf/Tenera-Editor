package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.scene.systems.ActorLayerSystem;


public class ActorLayerScene extends Scene implements ActorLayerSystem
{
 public ActorLayerScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  super.iterDraw(extrapolation);

  drawLayers(batch, extrapolation);
 }
}
