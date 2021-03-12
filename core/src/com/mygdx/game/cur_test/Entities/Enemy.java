package com.mygdx.game.cur_test.Entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.cur_test.SpriteManager;

public class Enemy extends MovingObject
{
 public static Vector2 vector2 = new Vector2();

 public Enemy(Vector2 position)
 {
  super(position, vector2.set(3,6), SpriteManager.textures.get("enemy"));
  StickmanWorld.simpleBehaviourSystem.addActor(this);
 }
}
