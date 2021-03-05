package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;

public class ExtrapolationDrawingSystem extends DrawingSystem
{
 private Vector2 velocity;

 @Override
 protected void loadFields()
 {
  super.loadFields();
  velocity = BasePhysicsComponent.get(actor).velocity;
 }

 @Override
 protected void behave()
 {
  float extr = ApplicationLoop.instance.extrapolation;
  batch.draw(texture, position.x+velocity.x* extr, position.y+velocity.y* extr, size.x, size.y);
 }
}
