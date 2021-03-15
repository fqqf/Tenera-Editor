package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class AnimationComponent extends Component<AnimationComponent>
{
 public static class Data
 {
  final int frequency;
  public final Array<TextureAtlas.AtlasRegion>frames;
  public Data(int frequency, TextureAtlas.AtlasRegion ... frames)
  {
   if (frames.length == 0)throw new IllegalArgumentException("Animation cannot be empty!");
   this.frequency = frequency;
   this.frames = new Array<>(frames);
  }
  public boolean next(int currentFrequencyAccum)
  {
   return currentFrequencyAccum >= frequency;
  }
 }
 private static final ComputeableHashMap<AnimationComponent> childList = new ComputeableHashMap<>();
 public final ObjectMap<Integer,Data> animStates = new ObjectMap<>();

 public int currentFrequencyAcum,currentState,currentIndexAnim;

 public AnimationComponent(Actor actor)
 {
  super(actor);
 }
 public AnimationComponent addAnimation(int state, int frequency, TextureAtlas.AtlasRegion...frames)
 {
  if (animStates.containsKey(state))throw new IllegalArgumentException("there is already such a state, something went wrong ...");
  animStates.put(state, new Data(frequency, frames));
  return this;
 }
 public void setAnimation(int animationState)
 {
  if (!animStates.containsKey(animationState))throw new IllegalArgumentException("no this state, something went wrong ...");
  this.currentState = animationState;
  currentFrequencyAcum = 0;
  currentIndexAnim = 0;
 }

 public static AnimationComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new AnimationComponent(actor));
 }
}
