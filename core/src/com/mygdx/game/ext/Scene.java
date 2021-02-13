package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.*;

public class Scene
{
 public static final int MAX_DRAW_LAYERS = 127;
 private final String name;
 private final View view;
 private final Field field;
 private final SpriteBatch batch;
 private final ShapeRenderer liner;
 private final Map<String, Dobject> dobjects = new HashMap<>();
 private final List<Dobject> sortedDobjectsForDraw = new ArrayList<>();
 private final List<Integer> layers = new ArrayList<>();

 private boolean once;

 // TODO: RENDER SCENE GRID
 float width=1000, height=10;

 public Scene(String name, Field field)
 {
  this.name = name;
  this.field = field;
  this.view = field.view;
  this.batch = field.batch;
  this.liner = field.liner;
  this.camera = field.camera;
  View.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 /**Provides standard draw extrapolation system a-la: draw(curPos+speed*extrapolation)*/
 public void iterDraw(float extrapolation)
 {
  view.setField(field);


  batch.begin();
  //TODO: Add different layers support 0->128
  //dobjects.forEach((k,obj) -> obj.draw(extrapolation));

  for (Dobject dobject : sortedDobjectsForDraw) dobject.draw(extrapolation);

  batch.end();

  field.cameraController.setPosition(-dobjects.get("hero").position.x-dobjects.get("hero").speed.x*extrapolation+4,0);
  drawGrid();
 }

 public void iterPhys()
 {
  dobjects.forEach((k,obj) -> obj.behave());


 }

 public void iterInput()
 {

 }

 public Dobject removeObject(String name)
 {
  Dobject dobject = dobjects.remove(name);
  if ( dobject != null )
  {
   sortedDobjectsForDraw.remove( dobject );
   Collections.sort(sortedDobjectsForDraw, Comparator.comparingInt(dobject2 -> dobject2.drawLayerNumb));
  }
  return dobject;
 }

 public Dobject addObject(Dobject dobject, String name)
 {
  dobjects.put(name, dobject);
  sortedDobjectsForDraw.add( dobject );
  Collections.sort(sortedDobjectsForDraw, Comparator.comparingInt(dobject2 -> dobject2.drawLayerNumb));
  return dobject;
 }

 OrthographicCamera camera;

 private void drawGrid()
 {
  camera.update();

  liner.setProjectionMatrix(camera.combined);
  batch.setProjectionMatrix(camera.combined);

  liner.begin(ShapeRenderer.ShapeType.Line);

  liner.setColor(Color.BLACK);
  for (int i = 0; i < width; i++) liner.line(i, 0, i, height);
  for (int i = 0; i < height; i++) liner.line(0, i, width, i);


  liner.end();
 }
}

