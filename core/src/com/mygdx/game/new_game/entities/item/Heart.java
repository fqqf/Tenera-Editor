package com.mygdx.game.new_game.entities.item;

import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.entities.stat.Envy;

public class Heart extends Envy
{
 public Heart(float x, float y)
 {
  super(x, y, 1.8f, 1.9f, SpriteManager.textures.get("heart"));
 }
}
