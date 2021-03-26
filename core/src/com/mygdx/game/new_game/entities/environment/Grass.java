package com.mygdx.game.new_game.entities.environment;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;

public class Grass extends Actor
{
 public Grass(float x, float y)
 {
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

  bodyPropertiesComponent.position.set(x,y);
  bodyPropertiesComponent.size.set(11.75f, 1.71f);

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = SpriteManager.textures.get("grass");

  // collisionComponent.box.setPosition(x,y);
  // collisionComponent.box.setSize(width, height);
 }
}
