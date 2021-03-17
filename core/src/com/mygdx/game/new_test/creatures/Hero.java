package com.mygdx.game.new_test.creatures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Hero extends Moveable
{
 public Hero(float x, float y)
 {
  super(x, y, 2.10f, 5.20f, SpriteManager.textures.get("hero1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
          .addAnimation(new AnimationData(false,2.10f,5.20f,0.2f, Animation.PlayMode.LOOP,
                          SpriteManager.textures.get("hero1"),
                          SpriteManager.textures.get("hero2"),
                          SpriteManager.textures.get("hero3")
                  ));

  Systems.animationSystem.addActor(this);
  Systems.controlSystem.addActor(this);
 }
}
