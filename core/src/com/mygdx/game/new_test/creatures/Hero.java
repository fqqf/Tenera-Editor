package com.mygdx.game.new_test.creatures;

import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Hero extends Moveable
{
 public Hero(float x, float y)
 {
  super(x, y, 2.10f, 5.20f, SpriteManager.textures.get("hero1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent.addAnimation(0,60_000_000,
    SpriteManager.textures.get("hero1"),
    SpriteManager.textures.get("hero2"),
    SpriteManager.textures.get("hero3")
  );

  Systems.animationSystem.addActor(this);
  Systems.controlSystem.addActor(this);
 }
}
