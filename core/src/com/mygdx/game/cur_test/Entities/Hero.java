package com.mygdx.game.cur_test.Entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.cur_test.SpriteManager;

public class Hero extends MovingObject
{
 public static Vector2 vector2 = new Vector2();

 public Hero(Vector2 position)
 {
  super(position, vector2.set(1.56f,3.89f), SpriteManager.textures.get("hero"));
  StickmanWorld.controlSystem.addActor(this);
 }
}
