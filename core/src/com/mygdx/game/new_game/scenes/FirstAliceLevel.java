package com.mygdx.game.new_game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.event.presets.PlaySound;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.entities.Alice;
import com.mygdx.game.new_game.entities.InvisibleWall;
import com.mygdx.game.new_game.entities.background.Background;
import com.mygdx.game.new_game.entities.stat.*;
import com.mygdx.game.new_game.events.SpawnGear;

public class FirstAliceLevel extends Scene
{
 public static Layer drawLayer = new Layer(null);

 public static Layer npc = new Layer(null);
 public static Layer alicel = new Layer(null);
 public static Layer background = new Layer(null);
 public static Layer effects = new Layer(null);
 public static Layer grass = new Layer(null);
 public static Layer cutscene = new Layer(null);
 public static Layer environment = new Layer(null);
 public static Layer events = new Layer(null);
 public static Layer interfaceL = new Layer(null);

 public Alice alice = new Alice(2,5);

 public FirstAliceLevel(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  setSceneSystems();
  configureLayers();

  DrawingSystem.DEBUG = true;

  putActors();

  Systems.keyBoardSystem.cameraController = field.cameraController;
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.32156f, 0.32156f, 0.32156f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  drawGrid();

  super.iterDraw(extrapolation);
 }

 private void putActors()
 {
  alicel.add(alice);
  grass.add(new InvisibleWall(0,0,1000,0.1f),new InvisibleWall(0,0,0.1f,15));

  for (int i = 0; i < 100; i++) grass.add(new Grass(i*11.73f,0));

  background.add(new Background());

  environment.addAll(
    new Cross(60,0), new Tower(100,0), new Stump(68,0), new TreeA(74,0), new Haunted(83,0), new TreeC(90,0), new TreeB(95,0)
  );

  VignetteRect vignette = new VignetteRect(0,0);
  VignetteRect vignetteTop = new VignetteRect(0,12-7.11f);
  DrawingComponent.get(vignette).spriteColor.set(0.54f,0.196f,0.196f,1);
  DrawingComponent.get(vignette).flipY = true;
  DrawingComponent.get(vignetteTop).spriteColor.set(0,0,0,0.7f);

  background.add(vignette);
  effects.add(vignetteTop);

  alice.addHeart();
  alice.addHeart();
  alice.addHeart();

  Systems.eventSystem.setMaster(alice).addEvent(new PlaySound(10,10), new SpawnGear(30,0));
 }

 private void configureLayers()
 {
  ExtendCoordinateGrid cameraGrid = new ExtendCoordinateGrid("camera-grid",12);
  npc.setCoordinateGrid(field);
  background.setCoordinateGrid(cameraGrid);
  effects.setCoordinateGrid(cameraGrid);
  grass.setCoordinateGrid(field);
  alicel.setCoordinateGrid(field);
  cutscene.setCoordinateGrid(field);
  environment.setCoordinateGrid(field);
  events.setCoordinateGrid(field);
  interfaceL.setCoordinateGrid(cameraGrid);

  Systems.drawingSystem.layers.put(4, background);
  Systems.drawingSystem.layers.put(5, environment);
  Systems.drawingSystem.layers.put(6, npc);
  Systems.drawingSystem.layers.put(7, alicel);
  Systems.drawingSystem.layers.put(8, grass);
  Systems.drawingSystem.layers.put(9, effects);
  Systems.drawingSystem.layers.put(10, interfaceL);

  Systems.eventSystem.setLayer(events);
 }

 private void setSceneSystems()
 {
  addSystem(System.Type.RENDER_SYSTEM,
    Systems.keyBoardSystem,
    Systems.animationSystem,
    Systems.drawingSystem
  );
  addSystem(System.Type.PHYSICS_SYSTEM,
    Systems.aliceBehaviourSystem,
    Systems.physicsSystem,
    Systems.eventSystem,
    // Systems.gravitySystem,
    Systems.collisionSystem
  );
 }

 @Override
 public void iterPhys()
 {
  super.iterPhys();
 }
}
