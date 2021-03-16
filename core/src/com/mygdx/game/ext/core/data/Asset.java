package com.mygdx.game.ext.core.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ObjectMap;

public class Asset extends AssetManager
{
 private final ObjectMap<Integer, String> resourceMap = new ObjectMap<>();

 public <T> void load(String fileName, Class<T> type, int resId)
 {
  if (resourceMap.containsKey(resId)) throw new IllegalArgumentException("this resId is already in use!");
  else resourceMap.put(resId, fileName);
  load(fileName, type);
 }

 public TextureAtlas.AtlasRegion getAtlasRegion(String name, int resId)
 {
  String fileName = resourceMap.get(resId);
  TextureAtlas ta = super.get(fileName);
  return ta.findRegion(name);
 }

 @Override
 public synchronized void dispose()
 {
  resourceMap.clear();
  super.dispose();
 }
}
