package com.mygdx.game.cur_test.Environment;

import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Grass extends EnvironmentObject
{
 public Grass(float x, float y)
 {
  super(x, y, 17.78f, 0.71f, SpriteManager.textures.get("long_grass"));

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setSize(collisionComponent.box.getWidth(), collisionComponent.box.getHeight()-0.5f);
 }
}
