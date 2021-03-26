package com.mygdx.game.new_game.entities.environment;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BodyPropertiesComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.new_game.SpriteManager;

public class Envy extends Actor
{
 public Envy(float x, float y, float width, float height, TextureAtlas.AtlasRegion texture)
 {
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(this);

  bodyPropertiesComponent.position.set(x,y);
  bodyPropertiesComponent.size.set(width, height);

  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = texture;
 }

}
