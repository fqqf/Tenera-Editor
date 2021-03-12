package com.mygdx.game.cur_test.Environment;

import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Castle extends EnvironmentObject
{
 public Castle(float x, float y)
 {
  super(x, y, 4.31f, 6.23f, SpriteManager.textures.get("castle"));

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setOffset(0.7f,0);
  collisionComponent.box.setSize(collisionComponent.box.getWidth()-1.7f, collisionComponent.box.getHeight()-0.8f);
 }
}
