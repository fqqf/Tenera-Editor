package com.mygdx.game.new_game.events;

import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.new_game.AliceBehaviourSystem;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.entities.Alice;
import com.mygdx.game.new_game.entities.item.Gear;
import com.mygdx.game.new_game.scenes.FirstAliceLevel;

public class SpawnGear extends Event
{

 public SpawnGear(float x, float y)
 {
  super(x, y);
 }

 @Override
 public void play()
 {
  Alice alice = AliceBehaviourSystem.getAlice();
  BodyPropertiesComponent bp = BodyPropertiesComponent.get(alice);
  FirstAliceLevel.npc.add(gear = new Gear(bp.position.x+20, bp.position.y+10));

  eventSystemInstance.removeList.add(this);
  eventSystemInstance.playingNow.add(this);
 }


 Gear gear;

 @Override
 public void continuePlaying()
 {
  PhysicsComponent physicsComponent = PhysicsComponent.get(gear);

  // TODO:  drawingComponent.rotate

  physicsComponent.velocity.x=-0.9f;

  if (gear.touchedAlice)
  {
   eventSystemInstance.removeList.add(this);
   FirstAliceLevel.npc.remAll(gear);
   Systems.collisionSystem.remActor(gear);
  }

 }
}
