package com.mygdx.game.cur_test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.cur_test.actors.Box;
import com.mygdx.game.cur_test.actors.Hero;
import com.mygdx.game.cur_test.actors.Water;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.presets.GameScene;

public class DelightfulLocation extends GameScene
{
 public Group entitiesLayer = new Group();

 public DelightfulLocation(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  entitiesLayer.addAll(new Hero(), new Water(), new Box());

  layers.put(1, entitiesLayer);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  super.iterDraw(extrapolation);

 }
}
