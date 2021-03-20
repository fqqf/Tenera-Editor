package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;
import com.mygdx.game.ext.core.components.presets.animation.AnimationData;

public class DrawingComponent extends Component
{
 private static final ComputeableHashMap<DrawingComponent> childList = new ComputeableHashMap<>();
 private static final TextureAtlas.AtlasRegion nullAtlasRegion;
 static
 {
  Texture texture = new Texture("box.png");
  nullAtlasRegion = new TextureAtlas.AtlasRegion( texture,0,0, 0, 0);
 }

 public TextureAtlas.AtlasRegion atlasRegion;
 public boolean showStatic = true;
 public final Array<AnimationData> animations = new Array<>(5);
 public boolean useExtrapolation = false;
 public boolean extrapolationX = true;
 public boolean extrapolationY = true;
 public long extrapolationOffNanoX = 0;
 public long extrapolationOffNanoY = 0;
 public Color debugCollisionColor = Color.PINK;


 public DrawingComponent(TextureAtlas.AtlasRegion atlasRegion, Actor actor)
 {
  this.atlasRegion = atlasRegion;
 }

 public static DrawingComponent get(Actor actor)
 {
 // System.out.println(childList.compute(actor, () -> new DrawingComponent(nullTexture, actor)));
  return childList.compute(actor, () -> new DrawingComponent(nullAtlasRegion, actor));
 }
}
