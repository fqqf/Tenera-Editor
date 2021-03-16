package com.mygdx.game.new_test.environment;

import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Wind extends Intangible
{
 public Wind(float x, float y)
 {
  super(x, y, 20.63f*1.2f, 9.86f*1.2f, SpriteManager.textures.get("wind1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent.addAnimation(0,3,
    SpriteManager.textures.get("wind1"),
    SpriteManager.textures.get("wind2"),
    SpriteManager.textures.get("wind3"));

  Systems.collisionManagmentSystem.remActor(this);
  CollisionComponent.remActor(this);
  Systems.animationSystem.addActor(this);
 }
}
