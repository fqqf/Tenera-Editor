package com.mygdx.game.cur_test.Environment;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Grass extends EnvObject
{
 public Grass(float x, float y)
 {
  super(x, y, 17.78f, 0.71f);

  setTexture(SpriteManager.textures.get("long_grass"));

  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setSize(collisionComponent.box.getWidth(), collisionComponent.box.getHeight()-0.5f);
 }
}
