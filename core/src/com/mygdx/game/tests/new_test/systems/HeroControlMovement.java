package com.mygdx.game.tests.new_test.systems;
/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.system.presets.ControlSystem;
import com.mygdx.game.tests.new_test.environment.Hop;

public class HeroControlMovement extends ControlSystem
{
 private Hop hop;

 public HeroControlMovement()
 {
  hop = new Hop(50,50);
 }

 @Override
 protected void behave()
 {
  // logger.info("Control System");

  if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {velocity.y=0; speed.y = 0.7f;
   BodyPropertiesComponent.get(hop).position.set(
     BodyPropertiesComponent.get(actor).position.x-1.7f,BodyPropertiesComponent.get(actor).position.y-2f);

  }
  if (Gdx.input.isKeyPressed(Input.Keys.A)) speed.x = -0.03f;
  if (Gdx.input.isKeyPressed(Input.Keys.D)) speed.x =  0.03f;

  if (!speed.isZero())
  {
   velocity.add(speed);
   speed.setZero();
  }

  if (Math.abs(velocity.x)>0.7f) velocity.x = (velocity.x/Math.abs(velocity.x))*0.7f;

 }

 public Hop getHop()
 {
  return hop;
 }
}
*/