package com.mygdx.game.new_game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.HashMap;

public class SpriteManager
{
 private final static HashMap<String, Texture> textures0 = new HashMap<>(); // TODO: Remove from static
 public final static HashMap<String, TextureAtlas.AtlasRegion> textures = new HashMap<>();

 static
 {
  //textures0.put("npc", new Texture("test8/npc.png"));
//  textures0.put("platform", new Texture("test8/platform.png"));
  //textures0.put("enemy", new Texture("test8/enemy.png"));

  for (int i = 1; i < 5; i++) textures0.put("stand_a"+i, new Texture("hitobashira_demo/stand_a"+i+".png"));
  for (int i = 1; i < 7; i++) textures0.put("run_a"+i, new Texture("hitobashira_demo/run_a"+i+".png"));
  for (int i = 1; i < 8; i++) textures0.put("fight_a"+i, new Texture("hitobashira_demo/fight_a"+i+".png"));

  textures0.put("background", new Texture("hitobashira_demo/background/background.png"));

  // TODO: ВАЖНО!!! НУЖНО ТАКЖЕ ПЕРЕНЕСТИ ВСЕ ИЗ ПАПКИ hitobashira_demo/cutscenes

  String[] env = {"bush", "cross", "gates", "girl", "haunted", "poet", "stump", "tower", "tree1", "tree2", "tree3", "grass"};
  String[] item = {"fake_heart", "gear", "ghost", "heart"};

  for (String str: env) textures0.put(str, new Texture("hitobashira_demo/environment/"+str+".png"));
  for (String str: item) textures0.put(str, new Texture("hitobashira_demo/items/"+str+".png"));

  textures0.put("vignetteRect", new Texture("hitobashira_demo/effects/vignetteRect.png"));

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
