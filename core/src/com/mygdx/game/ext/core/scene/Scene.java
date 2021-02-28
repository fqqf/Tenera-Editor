package com.mygdx.game.ext.core.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.Monitor;
import com.mygdx.game.ext.core.group.Group;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Scene
{
 protected final String name;
 protected final Monitor monitor;
 protected final SpriteBatch batch;
 protected final ShapeRenderer liner;

 protected ExtendCoordinateGrid field;
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


//  batch.begin();
  //TODO: Add different layers support via group
  //for (Actor<?> actor: actors) actor.draw(extrapolation);
  //batch.end();
 }

 public void iterPhys()
 {
  for (Actor actor: actors) actor.act();
 }

 public void iterInput() { for (Actor actor: actors) actor.handleInput();}

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
