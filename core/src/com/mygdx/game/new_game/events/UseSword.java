package com.mygdx.game.new_game.events;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.animation.AnimationComponent;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.entities.Alice;

public class UseSword extends Event
{
 public boolean isPlaying = false;

 Alice alice;
 public UseSword(float x, float y, Alice alice)
 {
  super(x, y);
  this.alice = alice;
 }

 @Override
 public void play()
 {
  if (isPlaying) return;
  eventSystemInstance.playingNow.add(this);
  isPlaying = true;

  DrawingComponent.get(alice).draw = false;
  DrawingComponent.get(alice.fightAnimation).draw = true;
  AnimationComponent.get(alice.fightAnimation).animation.setPlayMode(Animation.PlayMode.NORMAL);
 }

 @Override
 public void continuePlaying()
 {
  System.out.println(AnimationComponent.get(alice.fightAnimation).animation);
 }
}
