package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OrthoCameraController
{
 private ExtendViewport viewport;
 private OrthographicCamera camera;
 private float VPK; // viewportTransformationCoefficient

 public OrthoCameraController(ExtendViewport viewport)
 {
  setViewport(viewport);
 }

 public void move(float x, float y)
 {

 }

 public void setViewport(ExtendViewport viewport)
 {
  this.viewport = viewport;
  this.camera = (OrthographicCamera) viewport.getCamera();
 }
}
