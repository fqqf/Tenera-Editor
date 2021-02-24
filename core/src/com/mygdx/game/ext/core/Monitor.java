package com.mygdx.game.ext.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;

import java.util.ArrayList;

/**
 * Manages different fields
 * */

public class Monitor
{
 public static Monitor instance;
 public static Monitor init()
 {
  if (instance != null) return instance;
  instance = new Monitor();
  return instance;
 }

 ArrayList<ExtendCoordinateGrid> fields;

 public static final Logger log = new Logger("CORE", Logger.INFO);

 public int
   pixelWidth = Gdx.graphics.getWidth(),
   pixelHeight = Gdx.graphics.getHeight();


 private Monitor()
 {
  fields = new ArrayList<>();

  batch = new SpriteBatch();
  liner = new ShapeRenderer();

  instance = this;

  Monitor.log.info("View system is active");
 }

 private final ShapeRenderer liner;
 private final SpriteBatch batch;

 public void update(int width, int height)
 {
  if (width==0 || height==0) {
   Monitor.log.error("Incorrect window size, pausing"); return;}

  this.pixelWidth = width;
  this.pixelHeight = height;

  for (ExtendCoordinateGrid field : fields) field.update();
 }

 /* Being called automatically, when field is created */
 public void addField(ExtendCoordinateGrid field)
 {
  fields.add(field);
 }

 public void setField(ExtendCoordinateGrid field)
 {
  batch.setProjectionMatrix(field.camera.combined);
  liner.setProjectionMatrix(field.camera.combined);
 }

 public SpriteBatch getBatch() { return batch; }
 public ShapeRenderer getLiner() { return liner; }
}
