package com.mygdx.game.new_game.entities.stat;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.SpriteManager;

public class Grass extends Envy
{
 public Grass(float x, float y)
 {
  super(x, y, 11.75f, 1.71f, SpriteManager.get("grass"));
 }
}
