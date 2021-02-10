package com.mygdx.game.ext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

/**
 * This view implementation
 * extends horizontal units, and keeps vertical the same */

public class View
{
 ArrayList<Field> fields;

 public static final Logger log = new Logger("CORE", Logger.INFO);

 public int
   pixelWidth = Gdx.graphics.getWidth(),
   pixelHeight = Gdx.graphics.getHeight();

 public float nonCeiledUnitWidth;

 public View()
 {
  fields = new ArrayList<>();

  batch = new SpriteBatch();
  liner = new ShapeRenderer();

  Singletones.view = this;

  View.log.info("View system is active");
 }

 private final ShapeRenderer liner;
 private final SpriteBatch batch;

 public void update(int width, int height)
 {
  if (width==0 || height==0) {View.log.error("Incorrect window size, pausing"); return;}

  this.pixelWidth = width;
  this.pixelHeight = height;

  for (Field field : fields)
  {
   calcFieldWidth(field);

   field.viewport.setMinWorldWidth(nonCeiledUnitWidth);
   field.viewport.update(pixelWidth, pixelHeight, true);
  }
 }

 Field field;
 public Field createField(String name, float unitHeight)
 {
  field = new Field(name, unitHeight, this);

  calcFieldWidth(field);
  field.viewport = new ExtendViewport(field.nonCeiledUnitWidth, field.unitHeight);
  field.camera = (OrthographicCamera) field.viewport.getCamera();
  field.batch = batch;
  field.liner = liner;

  fields.add(field);
  return field;
 }

 private void calcFieldWidth(Field field)
 {
  field.nonCeiledUnitWidth = (pixelWidth * field.unitHeight) / pixelHeight;
  field.unitWidth = (int) Math.ceil(field.nonCeiledUnitWidth);
 }

 public SpriteBatch getBatch() { return batch; }

 public void setField(Field field)
 {
  batch.setProjectionMatrix(field.camera.combined);
  liner.setProjectionMatrix(field.camera.combined);
 }
}
