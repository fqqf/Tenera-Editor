package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.new_test.SpriteManager;

public class InvisibilityButton extends Intangible
{
 public InvisibilityButton(float x, float y)
 {
  super(x, y, 3.5f, 3.5f, SpriteManager.textures.get("invisibility-button"));
 }
}
