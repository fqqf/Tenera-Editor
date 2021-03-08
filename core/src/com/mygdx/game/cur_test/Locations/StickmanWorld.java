package com.mygdx.game.cur_test.Locations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Entities.Stickman;
import com.mygdx.game.cur_test.Environment.Wall;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.presets.GameScene;

public class StickmanWorld extends GameScene
{
 public Group playersLayer = new Group();

 public StickmanWorld(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  playersLayer.add(new Stickman(new Vector2(10,4)));
  playersLayer.add(new Wall(10,0,5,2.5f));
  drawingSystem.layers.put(1,playersLayer);
 }

 @Override
 public void iterDraw(float ext)
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  super.iterDraw(ext);

  drawGrid();
 }
}
