package com.mygdx.game.new_test.creatures;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.new_test.SpriteManager;
import com.mygdx.game.new_test.Systems;

public class Npc extends Moveable
{
 public Npc(float x, float y)
 {
  super(x, y, 4.22f, 7.22f, SpriteManager.textures.get("npc1"));

  AnimationComponent animationComponent = AnimationComponent.get(this);

  animationComponent
          .addAnimation(AnimationComponent.STATE_IDLE, false,
                  new AnimationComponent.Data(4.22f,7.22f,0.2f, Animation.PlayMode.LOOP,
                          SpriteManager.textures.get("npc1"),
                          SpriteManager.textures.get("npc2"),
                          SpriteManager.textures.get("npc3")
                  )
          )
          .setAnimation(AnimationComponent.STATE_IDLE);

  Systems.collisionManagmentSystem.remActor(this);
  Systems.animationSystem.addActor(this);
 }
}
