package com.mygdx.game.new_game;

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


  textures0.put("run_a2", new Texture("hitobashira_demo/run_a2.png"));
  textures0.put("run_a3", new Texture("hitobashira_demo/run_a3.png"));
  textures0.put("run_a4", new Texture("hitobashira_demo/run_a4.png"));
  textures0.put("run_a5", new Texture("hitobashira_demo/run_a5.png"));
  textures0.put("run_a6", new Texture("hitobashira_demo/run_a6.png"));

  for (int i = 1; i < 7; i++) textures0.put("run_a"+i, new Texture("hitobashira_demo/run_a"+i+".png"));

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