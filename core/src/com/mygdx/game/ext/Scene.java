package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class Scene
{
 private final String name;
 private final View view;
 private final Field field;
 private final SpriteBatch batch;
 private HashMap<String, Dobject> dobjects = new HashMap<>();
 private boolean once;

 public Scene(String name, Field field)
 {
  this.name = name;
  this.field = field;
  this.view = field.view;
  this.batch = field.batch;
  View.log.info("Scene \""+name+"\" was created");

  field.camera.update();
  field.liner.setProjectionMatrix(field.camera.combined);
 }

 /**Provides standard draw extrapolation system a-la: draw(curPos+speed*extrapolation)*/
 public void iterDraw(float extrapolation)
 {
  view.setField(field);


  batch.begin();
  dobjects.forEach((k,obj) -> obj.draw(extrapolation));
  batch.end();

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
  return dobjects.remove(name);
 }

 public Dobject addObject(Dobject dobject, String name)
 {
  dobjects.put(name, dobject);
  return dobject;
 }
}

