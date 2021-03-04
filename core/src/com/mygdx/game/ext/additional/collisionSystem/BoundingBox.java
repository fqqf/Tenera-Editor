package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoundingBox extends Rectangle
{
 private final int type;

 public BoundingBox(int type)
 {
  this.type = type;
 }

 public int getType() { return type; }

 public void set(Vector2 position, Vector2 size)
 {
  set(position.x, position.y, size.x, size.y);
 }

}
