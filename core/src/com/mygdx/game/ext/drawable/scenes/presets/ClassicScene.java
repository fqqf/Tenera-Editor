package com.mygdx.game.ext.drawable.scenes.presets;

import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.groups.Group;
import com.mygdx.game.ext.drawable.scenes.Scene;

import java.util.Arrays;

public class ClassicScene<T extends Scene<T>> extends Scene<T>
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
