package com.mygdx.game.ext.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.Monitor;

@Deprecated
public class StandartActor
{
 protected Monitor monitor;
 protected SpriteBatch batch;
 protected Texture texture;
 public Vector2 speed, position;
 public float width, height;
 protected boolean isVisible = true;
 @Deprecated
 protected int drawLayerNumb = 0;

 {
  this.monitor = Monitor.instance;

  try
  {
  this.batch = monitor.getBatch();
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

 public StandartActor texture(Texture texture) { this.texture = texture; return this; }
 public StandartActor visibility(boolean visible) { isVisible = visible; return this; }
 public StandartActor size(float width, float height) { this.width = width; this.height = height; return this; }
 public StandartActor position(float x, float y) { this.position.x = x; this.position.y = y; return this;}
 public StandartActor scene(StandartScene standartScene, String name) {
  standartScene.addObject(this, name); return this;}

 @Deprecated
 public StandartActor setDrawLayer(int layerNumb)
 {
  if ( layerNumb > StandartScene.MAX_DRAW_LAYERS )
   throw new Error("Set drawLayer > Scene.MAX_DRAW_LAYERS! Set Scene.MAX_DRAW_LAYERS");
  drawLayerNumb = layerNumb;
  return this;
 }
}
