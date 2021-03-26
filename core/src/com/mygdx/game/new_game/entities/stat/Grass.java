package com.mygdx.game.new_game.entities.stat;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.new_game.SpriteManager;

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
