package com.mygdx.game.ext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

/**
 * Manages different fields
 * */

public class View
{
 ArrayList<Field> fields;

 public static final Logger log = new Logger("CORE", Logger.INFO);

 public int
   pixelWidth = Gdx.graphics.getWidth(),
   pixelHeight = Gdx.graphics.getHeight();

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

  for (Field field : fields) field.update();
 }

 /* Being called automatically, when field is created */
 public void addField(Field field)
 {
  fields.add(field);
 }

 public void setField(Field field)
 {
  batch.setProjectionMatrix(field.camera.combined);
  liner.setProjectionMatrix(field.camera.combined);
 }

 public SpriteBatch getBatch() { return batch; }
 public ShapeRenderer getLiner() { return liner; }
}
