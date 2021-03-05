package com.mygdx.game.tests.test4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.actor.presets.DullActor;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.ext.core.system.presets.ExtrapolationDrawingSystem;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.system.presets.MovementSystem;
/*
public class Cave extends Scene
{
 DullActor cube = new DullActor(), table = new DullActor();

 Component<Vector2> position, size;

 System
   movement = new MovementSystem(), drawing = new DrawingSystem(),
   extrapolationDrawing = new ExtrapolationDrawingSystem();

 @SuppressWarnings("unchecked")
 public Cave(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addActor(cube, table);

  cube.addComp(movement, extrapolationDrawing).addField("texture",new Component<>(new Texture("cube.png")));

  position = cube.getComponent("position");


  position.get().x = 5;

  size = cube.getComponent("size");
  position.get().x = 2; size.get().x = 4; size.get().y = 4;

  table.addComp(drawing).addField("texture", new Component<>(new Texture("table.png")));

  position = table.getComponent("position"); position.get().x = 15;
  size = table.getComponent("size"); size.get().x=4; size.get().y=8;
 }
}
*/