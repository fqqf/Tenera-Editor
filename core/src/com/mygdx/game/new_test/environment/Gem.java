package com.mygdx.game.new_test.environment;

import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Gem extends Static
{
 public Gem(float x, float y)
 {
  super(x, y, 2.17f, 4.06f, SpriteManager.textures.get("gem1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent.addAnimation(0,3,
    SpriteManager.textures.get("gem1"),
    SpriteManager.textures.get("gem2"),
    SpriteManager.textures.get("gem3"));

  Systems.collisionManagmentSystem.remActor(this);
  Systems.animationSystem.addActor(this);
 }
}
