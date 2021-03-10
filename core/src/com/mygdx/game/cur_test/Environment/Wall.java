package com.mygdx.game.cur_test.Environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class Wall extends Actor
{
 public Wall(float x, float y, float width, float height)
 {
  BasePhysicsComponent physicsComponent = BasePhysicsComponent.get(this);
  DrawingComponent drawingComponent = DrawingComponent.get(this);

  physicsComponent.position.set(x,y);
  physicsComponent.size.set(width, height);

  CollisionComponent collisionComponent = CollisionComponent.get(this);
  collisionComponent.box.setPosition(x,y);
  collisionComponent.box.setSize(width, height);

  drawingComponent.texture = (x/y==0) ? new Texture("test7/wall.png") : new Texture("test7/wall2.png");

  StickmanWorld.collisionManagmentSystem.addActor(this);
 }
}
