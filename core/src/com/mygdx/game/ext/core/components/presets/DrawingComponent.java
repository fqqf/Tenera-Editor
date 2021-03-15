package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class DrawingComponent extends Component<DrawingComponent>
{
 private static final ComputeableHashMap<DrawingComponent> childList = new ComputeableHashMap<>();
 private static final TextureAtlas.AtlasRegion nullAtlasRegion;
 static
 {
  Texture texture = new Texture("box.png");
  nullAtlasRegion = new TextureAtlas.AtlasRegion( texture,0,0, texture.getWidth(), texture.getHeight());
 }

 public TextureAtlas.AtlasRegion atlasRegion;
 public boolean useExtrapolation = false;

 public DrawingComponent(TextureAtlas.AtlasRegion atlasRegion, Actor actor)
 {
  super(actor);
  this.atlasRegion = atlasRegion;
 }

 public static DrawingComponent get(Actor actor)
 {
 // System.out.println(childList.compute(actor, () -> new DrawingComponent(nullTexture, actor)));
  return childList.compute(actor, () -> new DrawingComponent(nullAtlasRegion, actor));
 }
}
