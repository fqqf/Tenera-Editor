package com.mygdx.game.ext.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.core.Monitor;

import java.util.*;

@Deprecated
public class StandartScene
{
 public static final int MAX_DRAW_LAYERS = 127;
 private final String name;
 private final Monitor monitor;
 private final ExtendField field;
 private final SpriteBatch batch;
 private final ShapeRenderer liner;

 @Deprecated
 protected final Map<String, StandartActor> listDobjectsByName = new HashMap<>();
 @Deprecated
 private final List<StandartActor> sortedDobjectsForDraw = new ArrayList<>();
 @Deprecated
 private final List<Integer> layers = new ArrayList<>();

 public boolean drawGrid;

 private float width, height;

 // TODO: RENDER SCENE GRID

 public StandartScene(String name, ExtendField field, float width, float height)
 {
  this.name = name; this.field = field;
  this.monitor = field.monitor; this.camera = field.camera;
  this.batch = field.batch; this.liner = field.liner;
  this.width = width; this.height = height;

  Monitor.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 /**Provides standard draw extrapolation system a-la: draw(curPos+speed*extrapolation)*/
 public void iterDraw(float extrapolation)
 {
  monitor.setField(field);


  batch.begin();
  //TODO: Add different layers support 0->128
  //dobjects.forEach((k,obj) -> obj.draw(extrapolation));

  for (StandartActor standartActor : sortedDobjectsForDraw) standartActor.draw(extrapolation);

  batch.end();

 // field.cameraController.setPosition(-dobjects.get("hero").position.x-dobjects.get("hero").speed.x*extrapolation+4,0);
  if (drawGrid) drawGrid();
 }

 public void iterPhys()
 {
  listDobjectsByName.forEach((k, obj) -> obj.behave());
 }

 public void iterInput() { }

 @Deprecated
 public StandartActor removeObject(String name)
 {
  StandartActor standartActor = listDobjectsByName.remove(name);
  if ( standartActor != null )
  {
   sortedDobjectsForDraw.remove(standartActor);
   Collections.sort(sortedDobjectsForDraw, Comparator.comparingInt(dobject2 -> dobject2.drawLayerNumb));
  }
  return standartActor;
 }

 @Deprecated
 public StandartActor addObject(StandartActor standartActor, String name)
 {
  listDobjectsByName.put(name, standartActor);
  sortedDobjectsForDraw.add(standartActor);
  sortedDobjectsForDraw.sort(Comparator.comparingInt(dobject2 -> dobject2.drawLayerNumb));
  return standartActor;
 }

 @Deprecated
 public void addObjects(HashMap<String, StandartActor> dobjects)
 {
  dobjects.forEach((k,obj) -> {listDobjectsByName.put(k, obj); sortedDobjectsForDraw.add(obj);});
  sortedDobjectsForDraw.sort(Comparator.comparingInt(dobject2 -> dobject2.drawLayerNumb));
 }

 OrthographicCamera camera;

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
}

