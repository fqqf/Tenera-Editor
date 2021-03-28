package com.mygdx.game.new_game.drawing.stat;

import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.SpriteManager;

public class FakeHeart extends Heart
{
 public FakeHeart(float x, float y)
 {
  super(x, y);
  DrawingComponent.get(this).texture = SpriteManager.get("fake_heart");
  CollisionComponent.get(this).touch = fakeHeart;
 }
}
