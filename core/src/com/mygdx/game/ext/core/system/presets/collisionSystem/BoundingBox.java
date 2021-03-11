package com.mygdx.game.ext.core.system.presets.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoundingBox extends Rectangle
{
 private int type;
 public float halfWidth,halfHeight;
 public boolean staticObject = true;

 public BoundingBox(int type)
 {
  this.type = type;
 }

 public int getType() { return type; }
 public void setType(int type) {this.type = type;}

 public BoundingBox set(Vector2 position, Vector2 size)
 {
  setPosition(position.x, position.y);
  setSize(size.x,size.y);
  return this;
 }

 @Override
 public Rectangle set(float x, float y, float width, float height)
 {
  super.set(x, y, width, height);
  refreshHalfSize();
  return this;
 }

 public float getTop() { return y + height; }
 public float getRight() { return x + width; }

 @Override
 public Rectangle setSize(float width, float height)
 {
  super.setSize(width, height);
  refreshHalfSize();
  return this;
 }

 @Override
 public Vector2 getCenter(Vector2 vector)
 {
  vector.x = x + halfWidth;
  vector.y = y + halfHeight;
  return vector;
 }

 @Override
 public Rectangle setWidth(float width)
 {
  super.setWidth(width);
  halfWidth = this.width/2;
  return this;
 }

 @Override
 public Rectangle setHeight(float height)
 {
  super.setHeight(height);
  halfHeight = this.halfHeight/2;
  return this;
 }

 @Override
 public Rectangle setSize(float sizeXY)
 {
  super.setSize(sizeXY);
  refreshHalfSize();
  return this;
 }

 @Override
 public Rectangle set(Rectangle rect)
 {
  super.set(rect);
  refreshHalfSize();
  return this;
 }

 private void refreshHalfSize()
 {
  halfWidth = this.width/2;
  halfHeight = this.height/2;
 }
}
