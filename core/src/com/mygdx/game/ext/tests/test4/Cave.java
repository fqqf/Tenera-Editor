package com.mygdx.game.ext.tests.test4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.Handler;
import com.mygdx.game.ext.drawable.scenes.Scene;
import com.mygdx.game.ext.drawable.actors.presets.ClassicActor;
import com.mygdx.game.ext.drawable.components.DrawingHandler;
import com.mygdx.game.ext.drawable.components.ExtrapolationDrawingHandler;
import com.mygdx.game.ext.drawable.components.Field;
import com.mygdx.game.ext.drawable.components.MovementHandler;

public class Cave extends Scene<Cave>
{
 ClassicActor cube = new ClassicActor(), table = new ClassicActor();

 Field<Vector2> position, size;

 Handler<?>
   movement = new MovementHandler(), drawing = new DrawingHandler(),
   extrapolationDrawing = new ExtrapolationDrawingHandler();

 @SuppressWarnings("unchecked")
 public Cave(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addActor(cube, table);

  cube.addHandler(movement, extrapolationDrawing).addField("texture",new Field<>(new Texture("cube.png")));

  position = cube.getField("position");


  position.get().x = 5;

  size = cube.getField("size");
  position.get().x = 2; size.get().x = 4; size.get().y = 4;

  table.addHandler(drawing).addField("texture", new Field<>(new Texture("table.png")));

  position = table.getField("position"); position.get().x = 15;
  size = table.getField("size"); size.get().x=4; size.get().y=8;
 }
}
