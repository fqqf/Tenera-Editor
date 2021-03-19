package com.mygdx.game.ext.core.components.presets.animation;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class AnimationComponent extends Component<AnimationComponent>
{

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
 public final Array<AnimationData> animations = new Array<>();
 public boolean userStatic = true;

 public AnimationComponent(Actor actor)
 {
  super(actor);
 }
 public AnimationComponent addAnimation(AnimationData... animations)
 {
  this.animations.addAll(animations);
  return this;
 }

 public static AnimationComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new AnimationComponent(actor));
 }
}
