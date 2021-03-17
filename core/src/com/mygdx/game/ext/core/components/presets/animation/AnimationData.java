package com.mygdx.game.ext.core.components.presets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationData extends Animation<TextureAtlas.AtlasRegion>
{
 public boolean showStatic = true;
 public float offsetX,offsetY;
 public float width,height;
 public AnimationData(boolean showStatic, float width, float height, float frameDuration, PlayMode playMode, TextureAtlas.AtlasRegion... keyFrames)
 {
  super(frameDuration, new Array<>(keyFrames), playMode);
  setSize(width, height);
  this.showStatic = showStatic;
 }
 public AnimationData setOffset(float offsetX, float offsetY)
 {
  this.offsetX = offsetX;this.offsetY=offsetY;
  return this;
 }
 public AnimationData setSize(float width, float height)
 {
  this.width = width;this.height = height;
  return this;
 }
}
