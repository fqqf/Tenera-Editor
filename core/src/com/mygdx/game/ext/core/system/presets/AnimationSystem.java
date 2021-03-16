package com.mygdx.game.ext.core.system.presets;

import com.mygdx.game.ext.core.components.presets.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.system.System;

public class AnimationSystem extends System
{
 public AnimationSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 1;
 }
 private AnimationComponent animationComponent;
 private DrawingComponent drawingComponent;
 protected void loadFields()
 {
  animationComponent = AnimationComponent.get(actor);
  drawingComponent = DrawingComponent.get(actor);
 }
 protected void behave()
 {
   AnimationComponent.Data data = animationComponent.animStates.get(animationComponent.currentState);
   if ( data.next( animationComponent.currentFrequencyAcum += ApplicationLoop.instance.renderDelta ) )
   {
    animationComponent.currentFrequencyAcum = 0;
    if (++animationComponent.currentIndexAnim >= data.frames.size) animationComponent.currentIndexAnim = 0;
    drawingComponent.atlasRegion = data.frames.get(animationComponent.currentIndexAnim);
   }
 }
}
