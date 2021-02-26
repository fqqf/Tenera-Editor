package com.mygdx.game.ext.core.scene.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ext.core.actor.Actor;

import java.util.TreeMap;

public interface ActorLayerSystem
{
 TreeMap<Integer, Actor> layers = new TreeMap<>();

 default void drawLayers(SpriteBatch batch, float extrapolation)
 {
  batch.begin();
  layers.forEach((key, layer) -> layer.draw(extrapolation));
  batch.end();
 }
}
