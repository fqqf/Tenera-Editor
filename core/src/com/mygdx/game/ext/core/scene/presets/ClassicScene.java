package com.mygdx.game.ext.core.scene.presets;

import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.scene.Scene;

public class ClassicScene extends Scene
{
 public ClassicScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  super.iterDraw(extrapolation);

  batch.begin();
  for (Actor actor: actors) actor.draw(extrapolation);
  batch.end();
 }
}
