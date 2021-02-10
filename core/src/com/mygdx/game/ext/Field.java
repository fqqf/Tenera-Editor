package com.mygdx.game.ext;

/**
 * A coordinate grid in unit system.
 *
 * There is a static amount of units on one side,
 * and other is changing its amount to keep first static. */

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Field
{
 public View view;

 public ExtendViewport viewport;
 public OrthographicCamera camera;

 public String name;

 public SpriteBatch batch;
 public ShapeRenderer liner;

 public float
   unitWidth, unitHeight,
   nonCeiledUnitWidth;

 Vector2 fieldPosition = new Vector2(0,0);

 public Field(String name, float unitHeight, View view)
 {
  this.view = view; this.name = name;
  this.unitHeight = unitHeight;

  calcProportion();

  viewport = new ExtendViewport(nonCeiledUnitWidth, unitHeight); // max scaling properties (1920:1080)
  camera = (OrthographicCamera) viewport.getCamera();

  View.log.info("Field \""+name+"\" was created");
 }

 private void calcProportion()
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
}
