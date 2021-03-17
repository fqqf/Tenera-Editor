package com.mygdx.game.new_test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

public class SpriteManager
{
 private final static HashMap<String, Texture> textures0 = new HashMap<>();
 public final static HashMap<String, TextureAtlas.AtlasRegion> textures = new HashMap<>();

 static
 {
  //textures0.put("npc", new Texture("test8/npc.png"));
//  textures0.put("platform", new Texture("test8/platform.png"));
  //textures0.put("enemy", new Texture("test8/enemy.png"));

  textures0.put("hero1", new Texture("test8/hero1.png"));
  textures0.put("hero2", new Texture("test8/hero2.png"));
  textures0.put("hero3", new Texture("test8/hero3.png"));

  textures0.put("wind1", new Texture("test8/wind1.png"));
  textures0.put("wind2", new Texture("test8/wind2.png"));
  textures0.put("wind3", new Texture("test8/wind3.png"));

  textures0.put("npc1", new Texture("test8/npc1.png"));
  textures0.put("npc2", new Texture("test8/npc2.png"));
  textures0.put("npc3", new Texture("test8/npc3.png"));

  textures0.put("gem1", new Texture("test8/gem1.png"));
  textures0.put("gem2", new Texture("test8/gem2.png"));
  textures0.put("gem3", new Texture("test8/gem3.png"));

  textures0.put("ground", new Texture("test8/ground.png"));

  textures0.put("platform", new Texture("test7/platform.png"));
  init();
 }
 public static void init()
 {
  textures0.forEach(
          (key,value)->
          {
           TextureAtlas.AtlasRegion atlasRegion = new TextureAtlas.AtlasRegion(value,0,0,value.getWidth(), value.getHeight());
           atlasRegion.name = value.toString();
           textures.put(key, atlasRegion);
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
