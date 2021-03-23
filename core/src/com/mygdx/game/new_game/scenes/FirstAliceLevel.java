package com.mygdx.game.new_game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.entities.Alice;
import com.mygdx.game.new_game.entities.InvisibleWall;

public class FirstAliceLevel extends Scene
{
 public static Layer drawLayer = new Layer(null);

 public FirstAliceLevel(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  addSystem(
    Systems.collisionSystem, Systems.drawingSystem,
    Systems.animationSystem, Systems.collisionSystem,
    Systems.physicsSystem, Systems.aliceMovementControl
  );

  drawLayer.setCoordinateGrid(field);

  new InvisibleWall(0,1,100,0.1f);

  drawLayer.add(new Alice(1,1));

  Systems.drawingSystem.layers.put(1, drawLayer);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.32156f, 0.32156f, 0.32156f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  drawGrid();

  super.iterDraw(extrapolation);

 }

 @Override
 public void iterPhys()
 {
  super.iterPhys();
 }
}
