package com.mygdx.game.ext.drawable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.Monitor;
import com.mygdx.game.ext.drawable.actors.Actor;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Scene<T extends Scene<T>>
{
 //ArrayList<Section> layers; // Слои, отрисовываются по группам (упорядоченно)
// ArrayList<Section> dobjects; // Все объекты на сцене

 protected final String name;
 protected final Monitor monitor;
 protected final SpriteBatch batch;
 protected final ShapeRenderer liner;

 protected ExtendCoordinateGrid field;
 public boolean drawGrid;
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
  monitor.setField(field);

  batch.begin();
  //TODO: Add different layers support via group
  for (Actor actor: actors) actor.draw(extrapolation);
  batch.end();

  if (drawGrid) drawGrid();
 }

 public void iterPhys()
 {
  for (Actor<?> actor: actors) actor.act();
 }

 protected OrthographicCamera camera;

 private void drawGrid()
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

 // TODO actor type from Actor<T> and replace ArrayList with group
 protected ArrayList<Actor<?>> actors = new ArrayList<>();

 protected T actor(Actor<?>... actors) { this.actors.addAll(Arrays.asList(actors)); return (T) this; }
 protected Actor<?>[] removeActor(Actor<?>...actors) {this.actors.removeAll(Arrays.asList(actors)); return actors;}

 protected void addEvent() {}
}
