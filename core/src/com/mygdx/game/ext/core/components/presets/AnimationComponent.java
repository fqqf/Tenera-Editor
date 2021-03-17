package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class AnimationComponent extends Component<AnimationComponent>
{
 public static final int STATE_IDLE = 0;

 public static class Data extends Animation<TextureAtlas.AtlasRegion>
 {
  public float offsetX,offsetY;
  public float width,height;
  public Data(float width,float height, float frameDuration, PlayMode playMode, TextureAtlas.AtlasRegion... keyFrames)
  {
   super(frameDuration, new Array<>(keyFrames), playMode);
   setSize(width, height);
  }
  public Data setOffset(float offsetX,float offsetY)
  {
   this.offsetX = offsetX;this.offsetY=offsetY;
   return this;
  }
  public Data setSize(float width,float height)
  {
   this.width = width;this.height = height;
   return this;
  }

 }
// public static class Data
// {
//  final long frequency;
//  public final Array<TextureAtlas.AtlasRegion>frames;
//  public Data(long frequency, TextureAtlas.AtlasRegion ... frames)
//  {
//   if (frames.length == 0)throw new IllegalArgumentException("Animation cannot be empty!");
//   this.frequency = frequency;
//   this.frames = new Array<>(frames);
//  }
//  public boolean next(long currentFrequencyAccum)
//  {
//   return currentFrequencyAccum >= frequency;
//  }
// }
 private static final ComputeableHashMap<AnimationComponent> childList = new ComputeableHashMap<>();
 public final ObjectMap<Integer,Array<Data>> animStates = new ObjectMap<>();
 public final ObjectMap<Integer,Boolean> animStatesShowStatic = new ObjectMap<>();

 public int currentState = -1,newState = -1;

 public AnimationComponent(Actor actor)
 {
  super(actor);
 }
 public AnimationComponent addAnimation(int state, boolean showStatcTexture, Data... animations)
 {
  if (animStates.containsKey(state))throw new IllegalArgumentException("there is already such a state, something went wrong ...");
  animStates.put(state, new Array<>(animations) );
  animStatesShowStatic.put(state,showStatcTexture);
  return this;
 }
 public void setAnimation(int animationState)
 {
  if (!animStates.containsKey(animationState))throw new IllegalArgumentException("no this state, something went wrong ...");
  this.newState = animationState;
 }

 public static AnimationComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new AnimationComponent(actor));
 }
}
