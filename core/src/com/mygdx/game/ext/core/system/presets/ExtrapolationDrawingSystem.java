package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;

public class ExtrapolationDrawingSystem extends DrawingSystem
{
 private Vector2 velocity;
 private DrawingComponent drawingComponent;

 @Override
 protected void loadFields()
 {
  super.loadFields();
  velocity = BasePhysicsComponent.get(actor).velocity;
  drawingComponent = DrawingComponent.get(actor);
 }

 @Override
 protected void behave()
 {
  if (drawingComponent.extrapolation)
  {
   float extr = ApplicationLoop.instance.extrapolation;
   batch.begin();
   batch.draw(texture, position.x+velocity.x* extr, position.y+velocity.y* extr, size.x, size.y);
   batch.end();
  }
  else
  {
   super.behave();
   drawingComponent.extrapolationOffNano -= System.nanoTime();
   if ( drawingComponent.extrapolationOffNano <= 0 ) drawingComponent.extrapolation = true;
  }
 }
}
