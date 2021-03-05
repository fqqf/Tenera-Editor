package com.mygdx.game.ext.core.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.Monitor;

public abstract class Scene
{
 protected final String name;
 protected final Monitor monitor;
 protected final SpriteBatch batch;
 protected final ShapeRenderer liner;

 public ExtendCoordinateGrid field;
 protected float width, height;

 public Scene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  this.name = name; this.field = field;
  this.monitor = field.monitor; this.camera = field.camera;
  this.batch = field.batch; this.liner = field.liner;
  this.width = width; this.height = height;

  Monitor.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 public void iterDraw(float extrapolation)
 {
  camera.update();
  monitor.setField(field);

  callDrawSystems();

  draw(extrapolation);
 }

 protected void draw(float extrapolation) {}
 void act() {}
 void handleInput() {}

 public void iterPhys() { callPhysSystems(); act(); }

 public void iterInput() { callInputSystems(); handleInput(); }

 private int actStartIndex, inputStartIndex;

 private int getSystemTypeIndex(final System.Type systemType)
 { // TODO: Replace with normal switch-case
  return systemType == System.Type.GRAPHICS_SYSTEM ? 0 : systemType == System.Type.PHYSICS_SYSTEM ? actStartIndex : inputStartIndex;
 }

 private void updateIndexOnChange(final System.Type insertType, int value)
 {
  if (insertType == System.Type.GRAPHICS_SYSTEM)
  {
   actStartIndex += value;
   inputStartIndex += value;
  } else if (insertType == System.Type.PHYSICS_SYSTEM) inputStartIndex += value;
 }

 protected void callDrawSystems()
 {
  callSystems(0, actStartIndex);
 }

 protected void callPhysSystems()
 {
  callSystems(actStartIndex, inputStartIndex);
 }

 protected void callInputSystems()
 {
  callSystems(inputStartIndex, systems.size);
 }

 protected void callSystems(final int startIndewx, final int endIndex)
 {
  for (int i = startIndewx; i < endIndex; i++) systems.get(i).handle();
 }

 private final Array<System> systems = new Array<>();

 public void addSystem(System... systems) { for (System system : systems) addSystem(system); }

 private void addSystem(System system)
 {
  int index = getSystemTypeIndex(system.getType());
  systems.insert(index, system);
  updateIndexOnChange(system.getType(), 1);
 }

 public void remSystem(System... systems) { for (System system : systems) remSystem(system); }
 private void remSystem(System system) { if (systems.removeValue(system, true)) updateIndexOnChange(system.getType(), -1); }

 protected OrthographicCamera camera;

 protected void drawGrid()
 {
  camera.update();

  liner.setProjectionMatrix(camera.combined);
  batch.setProjectionMatrix(camera.combined);

  liner.begin(ShapeRenderer.ShapeType.Line);

  liner.setColor(Color.RED);
  for (int i = 0; i < width; i++) liner.line(i, 0, i, height);
  for (int i = 0; i < height; i++) liner.line(0, i, width, i);

  liner.end();
 }

 protected Group actors = new Group();

 //private static Array<Actor> tmpArray = new Array<>(true,8);//not thread safe!
 protected Scene addActor(Actor... actors) { this.actors.addAll(Array.with(actors)); return this; }
 protected Actor[] remActor(Actor...actors) {this.actors.removeAll(Array.with(actors),true); return actors;}
}
