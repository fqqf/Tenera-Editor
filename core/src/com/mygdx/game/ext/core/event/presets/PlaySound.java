package com.mygdx.game.ext.core.event.presets;

import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.entities.Gear;

public class PlaySound extends Event
{
 @Override
 public void play()
 {
  for (int i = 0, j=0; i < 5; i++, j+=2)
  {
   Systems.drawingSystem.layers.get(6).add(new Gear(4+j,5+i));
  }

  Systems.drawingSystem.layers.get(6).add(new Gear(5,3));

  eventSystemInstance.events.pop();
 }
}
