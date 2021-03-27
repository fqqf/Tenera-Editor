package com.mygdx.game.new_game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.World;
import com.mygdx.game.ext.additional.CameraController;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;
import com.mygdx.game.ext.core.components.presets.movement.MovingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.ControlSystem;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;
import com.mygdx.game.new_game.entities.Alice;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class KeyBoardSystem extends System
{
 public KeyBoardSystem()
 {
  type = System.Type.RENDER_SYSTEM;
 }

 public static boolean LEFT, RIGHT, UP, BUTTON;
 public CameraController cameraController;

 @Override
 protected void loadFields()
 {

 }

 @Override
 public void handle()
 {
  behave();
 }

 @Override
 protected void behave()
 {
  // TODO ДЛЯ ЭТОЙ СИСТЕМЫ
  //  flipX flipY
  //  replace with updatePhysics()
  //  replace Math.abs(x)/x with sign
  //  add Y-axis

  if (!LEFT) LEFT = Gdx.input.isKeyPressed(Input.Keys.A);
  if (!RIGHT) RIGHT = Gdx.input.isKeyPressed(Input.Keys.D);
  if (!UP) UP = Gdx.input.isKeyJustPressed(Input.Keys.W);
  if (!BUTTON) BUTTON = Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);

  if (LEFT) {
   if (!RIGHT) {
    DrawingComponent.get(AliceBehaviourSystem.getAlice()).flipX = true;

    DrawingComponent.get(AliceBehaviourSystem.getAlice()).offset.set(0,0);
   }

  } // TODO: Move from here to special graphics class. ( GRAPHIC SWITCHER CLASS )
  if (RIGHT) {if (!LEFT) {
   DrawingComponent.get(AliceBehaviourSystem.getAlice()).flipX = false;
   DrawingComponent.get(AliceBehaviourSystem.getAlice()).offset.set(-1.9f,0);
  }
  }



  Alice alice = AliceBehaviourSystem.getAlice();
  DrawingComponent fightAnimationDrawingComponent = DrawingComponent.get(alice.fightAnimation);
  BodyPropertiesComponent bp = BodyPropertiesComponent.get(alice);
  PhysicsComponent pc = PhysicsComponent.get(alice);

  DrawingComponent dc = DrawingComponent.get(alice);


  fightAnimationDrawingComponent.offset.x = (fightAnimationDrawingComponent.flipX) ? -2.95f : 0.5f;

  posx = (dc.extrapolationX) ? -bp.position.x-pc.velocity.x* ApplicationLoop.instance.extrapolation+5 : -bp.position.x+5;

  Systems.collisionSystem.updateActor(posx,0,alice);

  if (posx>-0.1f) posx = -0.1f; // TODO: MOVE EVERYTHING FROM HERE TO AliceBehaviourSystem analogue
  if (posx<-200f+FirstAliceLevel.npc.getCoordinateGrid().notIntegerUnitWidth) posx = -200f+FirstAliceLevel.npc.getCoordinateGrid().notIntegerUnitWidth;

  cameraController.setPosition(posx,0);
 }

 float posx;

 /*
 @Override
 protected void behave()
 {
  // TODO ДЛЯ ЭТОЙ СИСТЕМЫ
  //  flipX flipY
  //  replace with updatePhysics()
  //  replace Math.abs(x)/x with sign
  //  add Y-axis

  LEFT = Gdx.input.isKeyPressed(Input.Keys.A);
  RIGHT = Gdx.input.isKeyPressed(Input.Keys.D);
  UP = Gdx.input.isKeyJustPressed(Input.Keys.W);

  if (LEFT)     { speed.x += -0.04f;}
  if (RIGHT)     { speed.x +=  0.04f;}
  if (UP) speed.y = 3f;

  if (Math.abs(speed.x) > 0.8f) speed.x = (Math.abs(speed.x)/speed.x) * 0.8f;

  descendSpeedIfNotMoving();
  calculateChangeOfDirectionWhileMovingTheOppositeWay();

  /*if (velocity.x == 0) // ЭТОТ КОД НЕ ТРОГАТЬ
  {
   DrawingComponent.get(actor).draw=false;
   DrawingComponent.get(standingAnimation).draw = true;

   BodyPropertiesComponent.get(standingAnimation).position.set(BodyPropertiesComponent.get(actor).position);
  }*/
  /*logger.info(String.valueOf(speed.x));
 }

 private void descendSpeedIfNotMoving()
 {
  if (!(LEFT | RIGHT)) // Стрелка Пирса
  {
   if (Math.abs(speed.x)>0)
   {
    curSpeed.set(speed);
    curSpeed.x += -(Math.abs(speed.x)/speed.x) * 0.04f;
    changeOfSign = speed.x / Math.abs(speed.x) != curSpeed.x / Math.abs(curSpeed.x);
    if (changeOfSign) speed.x = 0; else {speed.x = curSpeed.x; Systems.physicsSystem.updateExtrapolationVelocity();} // TODO: REPLACE WITH updatePhysics();
   }
  }
 }

 private void calculateChangeOfDirectionWhileMovingTheOppositeWay()
 {
  if (LEFT && speed.x>0)
  {
   speed.x = 0f;
   Systems.physicsSystem.updateExtrapolationVelocity();
  }
  if (RIGHT && speed.x<0)
  {
   speed.x = 0f;
   Systems.physicsSystem.updateExtrapolationVelocity();
  }
 }*/
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

