package com.mygdx.game.cur_test;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class SpriteManager
{
 public static HashMap<String, Texture> textures = new HashMap<>();

 static
 {
  textures.put("grass", new Texture("test7/grass.png"));
  textures.put("long_grass", new Texture("test7/long_grass.png"));
  textures.put("castle", new Texture("test7/castle.png"));
  textures.put("platform", new Texture("test7/platform.png"));
 }
}
