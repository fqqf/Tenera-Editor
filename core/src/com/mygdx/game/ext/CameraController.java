package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraController
{

 private ExtendViewport viewport;
 private OrthographicCamera camera;
 private float VPC_X=1, VPC_Y=1; // viewportTransformationCoefficient
 private final float originWidth, originHeight;
 private float curWidth, curHeight;

 public CameraController(ExtendViewport viewport)
 {
  setViewport(viewport);

  curHeight = originHeight = viewport.getMinWorldHeight();
  curWidth = originWidth = viewport.getMinWorldWidth();

  setStartPosition();
 }

 public void setPosition(float x, float y)
 {
  camera.position.x = curWidth/2-x*VPC_X;
  camera.position.y = curHeight/2-y*VPC_Y;
 }

 public void move(float x, float y)
 {
  camera.translate(-x*VPC_X,-y*VPC_Y);
 }

 public void setStartPosition()
 {
  camera.position.x = curWidth/2;
  camera.position.y = curHeight/2;
 }

 public void update(float width, float height)
 {
  curWidth = width;
  curHeight = height;
  VPC_X = curWidth/originWidth;
  VPC_Y = curHeight/originHeight;

 // setStartPosition();
 }

 public void setViewport(ExtendViewport viewport)
 {
  this.viewport = viewport;
  this.camera = (OrthographicCamera) viewport.getCamera();
 }
}
