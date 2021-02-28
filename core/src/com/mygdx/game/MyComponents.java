package com.mygdx.game;

import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.component.presets.ControlComponent;
import com.mygdx.game.ext.core.component.presets.DrawingComponent;
import com.mygdx.game.ext.core.component.presets.ExtrapolationDrawingComponent;
import com.mygdx.game.ext.core.component.presets.MovementComponent;

public class MyComponents
{
 public static final Component
   extrapolationDrawing = new ExtrapolationDrawingComponent(),
   drawing = new DrawingComponent(),
   movement = new MovementComponent(),
   control = new ControlComponent();
}
