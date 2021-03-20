package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Hop extends Intangible
{
 public Hop(float x, float y)
 {
  super(x, y, 5, 5, SpriteManager.textures.get("hop1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
    .addAnimation(new AnimationData(5,5,0.3f, Animation.PlayMode.LOOP,
      SpriteManager.textures.get("hop1"),
      SpriteManager.textures.get("hop2"),
      SpriteManager.textures.get("hop3"))
    );

  animationComponent.userStatic = false;

  Systems.animationSystem.addActor(this);
 }
}
