package com.mygdx.game.cur_test.Locations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Entities.Stickman;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionManagmentSystem;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.presets.GameScene;
import com.mygdx.game.ext.core.system.presets.ControlSystem;
import com.mygdx.game.ext.core.system.presets.ExtrapolationDrawingSystem;
import com.mygdx.game.ext.core.system.presets.PhysicsSystem;

public class StickmanWorld extends GameScene
{
 public Group playersLayer = new Group();

 public StickmanWorld(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  addSystem(collisionManagmentSystem, controlSystem, extrapolationDrawingSystem, physicsSystem);

  playersLayer.add(new Stickman(new Vector2(4,0)));

  extrapolationDrawingSystem.layers.put(1,playersLayer);
 }

 @Override
 public void iterInput()
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  System.out.println(BasePhysicsComponent.get(playersLayer.get(0)).velocity);
  super.iterInput();
 }

 public static CollisionManagmentSystem collisionManagmentSystem = new CollisionManagmentSystem();
 public static ControlSystem controlSystem = new ControlSystem();
 public static ExtrapolationDrawingSystem extrapolationDrawingSystem = new ExtrapolationDrawingSystem();
 public static PhysicsSystem physicsSystem = new PhysicsSystem();
}
