package com.mygdx.game.ext.core.scene.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ext.core.group.Group;

import java.util.TreeMap;

public interface GroupLayerSystem
{
 TreeMap<Integer, Group> layers = new TreeMap<>();

 default void drawLayers(SpriteBatch batch, float extrapolation)
 {
  batch.begin();
  layers.forEach((key,layer) -> layer.forEach((actor -> actor.draw(extrapolation))));
  batch.end();
 }
}
