package com.mygdx.game.new_game.entities.environment;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.new_game.SpriteManager;

public class Bush extends Envy
{
 public Bush(float x, float y)
 {
  super(x, y, 4.42f, 3.57f, SpriteManager.textures.get("bush"));
 }
}
