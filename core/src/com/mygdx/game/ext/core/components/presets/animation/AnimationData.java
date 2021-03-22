package com.mygdx.game.ext.core.components.presets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AnimationData extends Animation<TextureAtlas.AtlasRegion>
{
 public AnimationData(float frameDuration, PlayMode playMode, TextureAtlas.AtlasRegion... keyFrames)
 {
  super(frameDuration, new Array<>(keyFrames), playMode);
 }
}
