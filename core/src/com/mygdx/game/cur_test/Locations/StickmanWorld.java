package com.mygdx.game.cur_test.Locations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Entities.Enemy;
import com.mygdx.game.cur_test.Entities.Hero;
import com.mygdx.game.cur_test.Environment.Castle;
import com.mygdx.game.cur_test.Environment.Grass;
import com.mygdx.game.cur_test.Environment.Platform;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.presets.GameScene;
import com.mygdx.game.ext.core.system.presets.behaviour.SimpleBehaviourSystem;

public class StickmanWorld extends GameScene
{
 public Group playersLayer = new Group(), objectsLayer = new Group(), creaturesLayer = new Group();



 public StickmanWorld(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addSystems();

  playersLayer.add(new Hero(new Vector2(0, 0)));

  objectsLayer.addAll(
    new Grass(0, 0),
    new Grass(17.78f, 0),
    new Castle(12,0),
    new Platform(4.5f,6.22f)
  );

  creaturesLayer.add(new Enemy(new Vector2(15+9*(float)Math.random(),0)));


  drawingSystem.layers.put(2,objectsLayer);
  drawingSystem.layers.put(1,playersLayer);
  drawingSystem.layers.put(3,creaturesLayer);
 }

 @Override
 public void iterDraw(float ext)
 {
  Gdx.gl.glClearColor(0, 0, 0, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  super.iterDraw(ext);
  //drawGrid();
 }

 public static SimpleBehaviourSystem simpleBehaviourSystem = new SimpleBehaviourSystem();

 private void addSystems()
 {
  addSystem(simpleBehaviourSystem);
 }
}

