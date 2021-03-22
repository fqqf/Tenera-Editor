package com.mygdx.game.tests.new_test.creatures;
/*
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;
import com.mygdx.game.tests.new_test.Systems;

public abstract class Moveable extends Actor
{
 public Moveable(float x, float y, float width, float height, TextureAtlas.AtlasRegion texture)
 {
  DrawingComponent drawingComponent = DrawingComponent.get(this);
  drawingComponent.texture = texture;
  drawingComponent.useExtrapolation = true;

  PhysicsComponent physicsComponent = PhysicsComponent.get(this);
  physicsComponent.position.set(x,y);
  physicsComponent.size.set(width, height);

  CollisionComponent cc = CollisionComponent.get(this);
  cc.box.setType(CollisionType.BODY);
  cc.box.setPosition(x,y);
  cc.box.setSize(physicsComponent.size.x, physicsComponent.size.y);

  Systems.physicsSystem.addActor(this);
  Systems.collisionSystem.addActor(this);
 }
}
*/