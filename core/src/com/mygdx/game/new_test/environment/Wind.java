package com.mygdx.game.new_test.environment;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Wind extends Static
{
 public Wind(float x, float y)
 {
  super(x, y, 20.63f*1.2f, 9.86f*1.2f, SpriteManager.textures.get("wind1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
          .addAnimation(AnimationComponent.STATE_IDLE,
                  new AnimationComponent.Data(20.63f*1.2f,9.86f*1.2f, 0.3f, Animation.PlayMode.LOOP,
                          SpriteManager.textures.get("wind1"),
                          SpriteManager.textures.get("wind2"),
                          SpriteManager.textures.get("wind3"))
          )
          .setAnimation(AnimationComponent.STATE_IDLE);

  Systems.collisionManagmentSystem.remActor(this);
  Systems.animationSystem.addActor(this);
 }
}
