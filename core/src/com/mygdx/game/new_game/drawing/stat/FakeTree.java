package com.mygdx.game.new_game.drawing.stat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;

public class FakeTree extends Envy
{
 public FakeTree(float x, float y)
 {
  super(x, y, 5.52f, 7.36f, SpriteManager.get("tree" + MathUtils.random(1,3)));
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.spriteColor = Color.BLACK;
  drawingComponent.offset.set(-(BodyPropertiesComponent.get(this).size.y/4 + 1/4f), 0);

  BodyPropertiesComponent.get(this).size.set(1, 6); // >:-0

  CollisionComponent cc = CollisionComponent.get(this);
  cc.collisionType = CollisionType.BODY;
  cc.touch = actor-> { if (actor instanceof Alice) ((Alice) actor).damage(); };
  Systems.collisionSystem.addActor(this);
 }
}
