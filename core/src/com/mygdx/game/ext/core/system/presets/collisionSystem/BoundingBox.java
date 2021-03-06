package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoundingBox extends Rectangle
{
 private int type;

 public BoundingBox(int type)
 {
  this.type = type;
 }

 public int getType() { return type; }
 public void setType(int type) {this.type = type;}

 public BoundingBox set(Vector2 position, Vector2 size) { set(position.x, position.y, size.x, size.y);return this; }

 public Vector2 getCenter(Vector2 vector)
 {
  vector.x = x + width/2;
  vector.y = y + height/2;
  return vector;
 }

}
