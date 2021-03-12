package com.mygdx.game.cur_test.Environment;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.cur_test.SpriteManager;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Platform extends EnvObject
{
 public Platform(float x, float y)
 {
  super(x, y, 6.91f, 1.56f);

  collisionComponent.box.setType(CollisionType.SOLID);
  collisionComponent.box.setOffset(0.25f, 0.3f);
  collisionComponent.box.setSize(collisionComponent.box.getWidth()-1.2f, collisionComponent.box.getHeight()-0.8f);


  setTexture(SpriteManager.textures.get("platform"));
 }


}
