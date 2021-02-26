package com.mygdx.game.tests.test4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.component.Component;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.actor.presets.DullActor;
import com.mygdx.game.ext.core.component.presets.DrawingComponent;
import com.mygdx.game.ext.core.component.presets.ExtrapolationDrawingComponent;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.component.presets.MovementComponent;

public class Cave extends Scene
{
 DullActor cube = new DullActor(), table = new DullActor();

 Field<Vector2> position, size;

 Component<?>
   movement = new MovementComponent(), drawing = new DrawingComponent(),
   extrapolationDrawing = new ExtrapolationDrawingComponent();

 @SuppressWarnings("unchecked")
 public Cave(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addActor(cube, table);

  cube.addComp(movement, extrapolationDrawing).addField("texture",new Field<>(new Texture("cube.png")));

  position = cube.getField("position");


  position.get().x = 5;

  size = cube.getField("size");
  position.get().x = 2; size.get().x = 4; size.get().y = 4;

  table.addComp(drawing).addField("texture", new Field<>(new Texture("table.png")));

  position = table.getField("position"); position.get().x = 15;
  size = table.getField("size"); size.get().x=4; size.get().y=8;
 }
}
