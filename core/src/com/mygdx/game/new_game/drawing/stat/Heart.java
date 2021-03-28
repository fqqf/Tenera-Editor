package com.mygdx.game.new_game.drawing.stat;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;

public class Heart extends Envy
{
 protected static Action.Arg1<Actor> heart = actor->
 {
  if (actor instanceof Alice) ((Alice) actor).addHeart();
 };

 protected static Action.Arg1<Actor> fakeHeart = actor->
 {
  if (actor instanceof Alice) ((Alice) actor).damage();
 };

 public Heart(float x, float y)
 {
  super(x, y, 2f, 2f, SpriteManager.get("heart"));
  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = heart;
  Systems.collisionSystem.addActor(this);
 }
}
