package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Wind extends Intangible
{
 public Wind(float x, float y)
 {
  super(x, y, 20.63f*1.2f, 9.86f*1.2f, SpriteManager.textures.get("wind1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
          .addAnimation(new AnimationData( 20.63f*1.2f,9.86f*1.2f, 0.3f, Animation.PlayMode.LOOP,
                          SpriteManager.textures.get("wind1"),
                          SpriteManager.textures.get("wind2"),
                          SpriteManager.textures.get("wind3"))
          );


  Systems.collisionSystem.remActor(this);//todo wtf?
  Systems.animationSystem.addActor(this);
 }
}
