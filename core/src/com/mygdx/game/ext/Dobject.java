package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Dobject
{
 protected View view;
 protected SpriteBatch batch;
 protected Texture texture;
 public Vector2 speed, position;
 public float width, height;
 protected boolean isVisible = true;
 protected int drawLayerNumb = 0;
 {
  this.view = Singletones.view;

  try
  {
  this.batch = view.getBatch();
  } catch (NullPointerException e) {throw new NullPointerException("Initialize View first!");}

  speed = new Vector2(); position = new Vector2();
 }

 public void draw(float ext) // ext - extrapolation
 {
  if (isVisible)
  {
   // TODO: Disable extrapolation while object is trying to collide
   batch.draw(texture, position.x+speed.x*ext, position.y+speed.y*ext, width, height);
  }
 }

 // Standart behaviour. You can remove it by not adding super.behave();
 public void behave()
 {
  position.x += speed.x; position.y += speed.y;
 }

 public Dobject setTexture(Texture texture) { this.texture = texture; return this; }
 public Dobject setVisibility(boolean visible) { isVisible = visible; return this; }
 public Dobject setSize(float width, float height) { this.width = width; this.height = height; return this; }
 public Dobject setPosition(float x, float y) { this.position.x = x; this.position.y = y; return this;}
 public Dobject setScene(Scene scene, String name) {scene.addObject(this, name); return this;}
 public Dobject setDrawLayer(int layerNumb)
 {
  if ( layerNumb > Scene.MAX_DRAW_LAYERS ) throw new Error("Set drawLayer > Scene.MAX_DRAW_LAYERS! Set Scene.MAX_DRAW_LAYERS");
  drawLayerNumb = layerNumb;
  return this;
 }
}
