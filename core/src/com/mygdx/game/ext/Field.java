package com.mygdx.game.ext;

/**
 * A coordinate grid in unit system.
 *
 * There is a static amount of units on one side,
 * and other is changing its amount to keep first static. */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Field
{
 private String name;

 public View view;

 public ExtendViewport viewport;
 public OrthographicCamera camera;

 public CameraController cameraController;

 public SpriteBatch batch;
 public ShapeRenderer liner;

 public float
   unitWidth, unitHeight,
   nonCeiledUnitWidth;

 public Field(String name, float unitHeight)
 {
  this.name = name;
  this.unitHeight = unitHeight;
  this.view = Singletones.view;
  
  calcFieldWidth();

  viewport = new ExtendViewport(nonCeiledUnitWidth, unitHeight);
  camera = (OrthographicCamera) viewport.getCamera();
  batch = view.getBatch();
  liner = view.getLiner();
  cameraController = new CameraController(viewport);

  view.addField(this);

  View.log.info("Field \""+name+"\" was created ["+unitWidth+":"+unitHeight+"]");
 }

 private void calcFieldWidth()
 {
  nonCeiledUnitWidth = (view.pixelWidth * unitHeight) / view.pixelHeight;
  unitWidth = (int) Math.ceil(nonCeiledUnitWidth);
 }

 public void debug(Color lines, Color center, Color lim)
 {
  camera.update();

  liner.setProjectionMatrix(camera.combined);
  batch.setProjectionMatrix(camera.combined);

  liner.begin(ShapeRenderer.ShapeType.Line);

  liner.setColor(lines);
  for (int i = 0; i < unitWidth; i++) liner.line(i, 0, i, unitHeight);
  for (int i = 0; i < unitHeight; i++) liner.line(0, i, unitWidth, i);

  liner.setColor(center);
  liner.line(0,unitHeight/2,nonCeiledUnitWidth,unitHeight/2);
  liner.line(nonCeiledUnitWidth/2,0,nonCeiledUnitWidth/2,unitHeight);

  liner.setColor(lim);
  liner.line(nonCeiledUnitWidth,0,nonCeiledUnitWidth,unitHeight);

  liner.end();
 }

 public void update()
 {
  calcFieldWidth();

  viewport.setMinWorldWidth(nonCeiledUnitWidth);
  viewport.update(view.pixelWidth, view.pixelHeight, false);

  cameraController.update(viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
 }
}
