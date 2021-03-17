package com.mygdx.game.new_test.creatures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Hero extends Moveable
{
 public Hero(float x, float y)
 {
  super(x, y, 2.10f, 5.20f, SpriteManager.textures.get("hero1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
          .addAnimation(AnimationComponent.STATE_IDLE,
                  new AnimationComponent.Data(2.10f,5.20f,0.2f, Animation.PlayMode.LOOP,
                          SpriteManager.textures.get("hero1"),
                          SpriteManager.textures.get("hero2"),
                          SpriteManager.textures.get("hero3")
                  ))
          .setAnimation(AnimationComponent.STATE_IDLE);

  Systems.animationSystem.addActor(this);
  Systems.controlSystem.addActor(this);
 }
}
