package com.mygdx.game.cur_test.Environment;

import com.mygdx.game.cur_test.SpriteManager;

public class Castle extends EnvObject
{
 public Castle(float x, float y)
 {
  super(x, y, 4.31f,6.23f);
  setTexture(SpriteManager.textures.get("castle"));
 }
}
