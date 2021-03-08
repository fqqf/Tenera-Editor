package com.mygdx.game.ext.core.system.presets;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;

@Deprecated
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
  System.out.print( "Drawing extrapolation " );
  if (drawingComponent.extrapolation)
  {
   System.out.println("on");
   float extr = ApplicationLoop.instance.extrapolation;
   batch.draw(texture, position.x+velocity.x*extr, position.y+velocity.y*extr, size.x, size.y);
  }
  else
  {
   System.out.println("off");
   super.behave();
   if ( drawingComponent.extrapolationOffNano <= System.nanoTime() )
   {
    drawingComponent.extrapolationOffNano = 0;
    drawingComponent.extrapolation = true;
   }
  }
 }
}
