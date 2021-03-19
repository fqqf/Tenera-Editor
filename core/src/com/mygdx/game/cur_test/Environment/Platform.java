package com.mygdx.game.cur_test.Environment;

import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Platform extends EnvironmentObject
{
 public Platform(float x, float y)
 {
  super(x, y, 6.91f, 1.56f, SpriteManager.textures.get("platform"));

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setOffset(0.25f, 0.3f);
  collisionComponent.box.setSize(collisionComponent.box.getWidth()-1.2f, collisionComponent.box.getHeight()-0.8f);
  collisionComponent.box.setType(CollisionType.PLATFORM);
 }
}
