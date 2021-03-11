package com.mygdx.game.cur_test.Environment;

import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Platform extends EnvObject
{
 public Platform(float x, float y)
 {
  super(x, y, 6.91f, 1.56f);

  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setPosition(1,1);
  collisionComponent.box.setSize(collisionComponent.box.getWidth()-1.2f, collisionComponent.box.getHeight()-0.5f);


  setTexture(SpriteManager.textures.get("platform"));
 }


}
