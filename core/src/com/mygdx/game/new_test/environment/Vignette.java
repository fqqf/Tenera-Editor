package com.mygdx.game.new_test.environment;

import com.mygdx.game.new_test.SpriteManager;

public class Vignette extends Intangible
{
 public Vignette(float x, float y)
 {
  super(x, y, 3.10f*100, 4.06f, SpriteManager.textures.get("vignette"));
 }
}
