package com.mygdx.game.cur_test;

import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;

public class Main extends ApplicationLoop
{
 // TODO : Система не проверяет, есть ли у актера n-ый компонент. Система проходится по своим внутренним актерам.
 //  Если у внутреннего актера системы нет компонента, он будет добавлен

 StickmanWorld stickmanWorld;

 @Override
 public void create()
 {
  super.create();

  stickmanWorld = new StickmanWorld("super-location",new ExtendCoordinateGrid("coordinate-grid", 10),100,100);
 }

 @Override
 public void drawGraphics()
 {
  stickmanWorld.iterDraw(extrapolation);
 }

 @Override
 public void handleInput()
 {
  stickmanWorld.iterInput();
 }

 @Override
 public void calcPhysics() { stickmanWorld.iterPhys(); }
}
