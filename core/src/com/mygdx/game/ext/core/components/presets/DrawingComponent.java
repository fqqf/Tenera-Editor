package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class DrawingComponent extends Component
{
 private static final ComputeableHashMap<DrawingComponent> childList = new ComputeableHashMap<>();
 private static final TextureAtlas.AtlasRegion defaultTexture;
 static
 {
  Texture texture = new Texture("box.png");
  defaultTexture = new TextureAtlas.AtlasRegion( texture,0,0, 0, 0);
 }

 public TextureAtlas.AtlasRegion texture;
 public boolean useExtrapolation = false;
 public boolean extrapolationX = true;
 public boolean extrapolationY = true;
 public long extrapolationOffNanoX = 0;
 public long extrapolationOffNanoY = 0;

 public Color debugCollisionColor = Color.PINK;


 public DrawingComponent(TextureAtlas.AtlasRegion texture, Actor actor)
 {
  this.texture = texture;
 }

 public static DrawingComponent get(Actor actor)
 {
 // System.out.println(childList.compute(actor, () -> new DrawingComponent(nullTexture, actor)));
  return childList.compute(actor, () -> new DrawingComponent(defaultTexture, actor));
 }
}
