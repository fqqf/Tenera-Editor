package com.mygdx.game.ext.core.system.presets;

import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.System;

public class AnimationSystem extends System
{
 public AnimationSystem()
 {
  type = Type.RENDER_SYSTEM;
  priority = 90;
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
  if (drawingComponent.animations.size == 0)
  {
   drawingComponent.animations.addAll(animationComponent.animations);
  }
 }
}
