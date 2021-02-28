package com.mygdx.game;

import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;

public class AwesomeGame extends ApplicationLoop
{
 DelightfulLocation location;

 @Override
 public void create()
 {
  super.create();

  location = new DelightfulLocation("super-location",new ExtendCoordinateGrid("coordinate-grid", 10),100,100);
 }

 @Override
 public void drawGraphics()
 {
  location.iterDraw(extrapolation);
 }

 @Override
 public void handleInput()
 {
  location.iterInput();
 }

 @Override
 public void calcPhysics() { location.iterPhys(); }
}
