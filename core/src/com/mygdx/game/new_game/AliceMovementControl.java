package com.mygdx.game.new_game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.system.presets.ControlSystem;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class AliceMovementControl extends ControlSystem
{

 boolean one = false;



 @Override
 protected void behave()
 {
  // logger.info("Control System");

  // TODO : Делать flipX модели при нажатии W или D соответственно. Модель получить через this.actor();
  if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {velocity.y=0; speed.y = 0.7f;}
  if (Gdx.input.isKeyPressed(Input.Keys.A)) speed.x = -0.03f;
  if (Gdx.input.isKeyPressed(Input.Keys.D)) { speed.x =  0.03f; DrawingComponent.get(actor).flipX = true; }

  if (!speed.isZero())
  {
   velocity.add(speed);
   speed.setZero();
  }

  if (Math.abs(velocity.x)>0.7f) velocity.x = (velocity.x/Math.abs(velocity.x))*0.7f;

  /*if (velocity.x == 0) // ЭТОТ КОД НЕ ТРОГАТЬ
  {
   DrawingComponent.get(actor).draw=false;
   DrawingComponent.get(standingAnimation).draw = true;

   BodyPropertiesComponent.get(standingAnimation).position.set(BodyPropertiesComponent.get(actor).position);
  }*/
 }
/*
 public StandingAnimation standingAnimation;

 public static class StandingAnimation extends Actor
 {
  public StandingAnimation()
  {
   BodyPropertiesComponent.get(this).size.set(3.71f, 6.61f);
   AnimationComponent animationComponent = AnimationComponent.get(this);
   animationComponent.animation = new AnimationData(0.09f, Animation.PlayMode.LOOP,
     SpriteManager.textures.get("stand_a1"),
     SpriteManager.textures.get("stand_a2"),
     SpriteManager.textures.get("stand_a3"),
     SpriteManager.textures.get("stand_a4")
   );

   Systems.animationSystem.addActor(this);
  }

 }
*/
}

