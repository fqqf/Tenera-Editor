package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraController
{

 private ExtendViewport viewport;
 private OrthographicCamera camera;
 private float VPC_X, VPC_Y; // viewportTransformationCoefficient
 private final float originWidth, originHeight;
 private float curWidth, curHeight;

 public CameraController(ExtendViewport viewport)
 {
  setViewport(viewport);

  originHeight = viewport.getMinWorldHeight();
  originWidth = viewport.getMinWorldWidth();

 }

 public void move(float x, float y)
 {
  camera.translate(x,y);
 }

 public void update(float width, float height)
 {
  curWidth = width;
  curHeight = height;
 }

 public void setViewport(ExtendViewport viewport)
 {
  this.viewport = viewport;
  this.camera = (OrthographicCamera) viewport.getCamera();
 }
}
