package com.mygdx.game.ext.drawable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ext.core.Monitor;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: Cloneable
public abstract class Actor<T extends Actor<T>> extends Dobject
{
 protected final Monitor monitor;
 protected Texture texture;
 protected float width, height;
 protected SpriteBatch batch;
 protected boolean isVisible = true;

 public T type;

 ArrayList<Component> components;

 public Actor()
 {
  System.out.println(type);
  this.monitor = Monitor.instance;

  try { this.batch = monitor.getBatch(); } catch (NullPointerException e)
  { throw new NullPointerException("Initialize Monitor first!"); }
 }

 public void draw(float ext)
 {
  if (isVisible)
  batch.draw(texture, position.x, position.y, width, height);
 }

 public void behave()
 {
  for (Component component : components) component.behave();
 }


 public T component(Component... components) { this.components.addAll(Arrays.asList(components)); return (T) this; }

 public T texture(Texture texture) { this.texture = texture; return (T) this; }

 public T visibility(boolean visible) { isVisible = visible; return (T) this; }

 public T size(float width, float height) { this.width = width; this.height = height; return (T) this; }

 public T position(float x, float y) { this.position.x = x; this.position.y = y; return (T) this;}

 public T scene(Scene<?> scene, String name) {scene.addActor(this); return (T) this;}
}


