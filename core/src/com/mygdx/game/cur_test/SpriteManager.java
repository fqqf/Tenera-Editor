package com.mygdx.game.cur_test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class SpriteManager
{
 private final static HashMap<String, Texture> textures0 = new HashMap<>();
 public final static HashMap<String, TextureAtlas.AtlasRegion> textures = new HashMap<>();

 static
 {
  textures0.put("grass", new Texture("test7/grass.png"));
  textures0.put("long_grass", new Texture("test7/long_grass.png"));
  textures0.put("castle", new Texture("test7/castle.png"));
  textures0.put("platform", new Texture("test7/platform.png"));
  textures0.put("enemy", new Texture("test7/enemy.png"));
  textures0.put("hero", new Texture("test7/hero.png"));

  textures0.forEach(
          (key,value)->
          {
           textures.put(key, new TextureAtlas.AtlasRegion(value,0,0,value.getWidth(), value.getHeight()));
          });

 }
 public static void dispose()
 {
  textures0.forEach( (key,value)->value.dispose());
  textures.forEach( (key,value)->value.getTexture().dispose());
  textures.clear();
  textures0.clear();
 }
}
