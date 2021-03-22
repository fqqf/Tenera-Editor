package com.mygdx.game.new_test.creatures;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
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
  animationComponent.animation = new AnimationData(0.10f, Animation.PlayMode.LOOP,
          SpriteManager.textures.get("hero1"),
          SpriteManager.textures.get("hero2"),
          SpriteManager.textures.get("hero3")
  );

  Systems.animationSystem.addActor(this);
  Systems.controlSystem.addActor(this);

  PhysicsComponent ph = PhysicsComponent.get(this);
  CollisionComponent cc = CollisionComponent.get(this);

  cc.box.setSize(ph.size.x*0.9f, ph.size.y*0.9f);                         // < for test
  cc.box.offset.set( ph.size.x/2 - cc.box.halfWidth, ph.size.y/2 - cc.box.halfHeight ); // < for test
 }
}
