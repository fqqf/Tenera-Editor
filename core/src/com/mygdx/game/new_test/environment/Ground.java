package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import com.mygdx.game.new_test.SpriteManager;

public class Ground extends Static
{
 public Ground(float x, float y)
 {
  super(x, y, 38.22f, 3.42f, SpriteManager.textures.get("ground"));

  BoundingBox box = CollisionComponent.get(this).box;

  box.setHeight(box.height-3);
 }
}