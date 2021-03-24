package com.mygdx.game.new_game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.components.presets.movement.JumpComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;

public class Alice extends Actor
{
 public Alice(float x, float y)
 {
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.textures.get("run_a1");
  drawingComponent.useExtrapolation = true;

  PhysicsComponent physicsComponent = PhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(5.14f/1.3f,6.49f/1.3f);

  CollisionComponent cc = CollisionComponent.get(this);
  cc.box.setType(CollisionType.BODY);
  cc.box.setPosition(x,y);
  cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.physicsSystem.addActor(this);
  Systems.collisionSystem.addActor(this);

  AnimationComponent animationComponent = AnimationComponent.get(this);
  animationComponent.animation = new AnimationData(0.09f, Animation.PlayMode.LOOP,
    SpriteManager.textures.get("run_a1"),
    SpriteManager.textures.get("run_a2"),
    SpriteManager.textures.get("run_a3"),
    SpriteManager.textures.get("run_a4"),
    SpriteManager.textures.get("run_a5"),
    SpriteManager.textures.get("run_a6")
  );

  Systems.animationSystem.addActor(this);
  Systems.aliceBehaviourSystem.setAlice(this);

  JumpComponent.get(this);
 }
}
