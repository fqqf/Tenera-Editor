package com.mygdx.game.ext.core.scene.presets;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ext.additional.CollisionDetector;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

/** This scene already includes AABB physics collision system, Event system, and drawing by layers */
public class GameScene extends GroupLayerScene
{
 // TODO:
 //  Collision Detector must contain an array of actors that should be checked for collisions
 //  Collision Manager creates that array (just takes all actors near player)
 //  You dont need a component for actor like CollisionComponent. All calculations are happening in collisionDetector
 //  In collisiondetector you should have method addActor, which does: actor.compute(AABBsize, Vector2) actor.compute(AABBposition, Vector2)
 CollisionDetector collisionDetector = new CollisionDetector();


 // TODO: CHECK IF PLAYERS POSITION IS NEAR EVENT BOUNDNINGBOX COMPONENT
 public GameScene(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
 }


 @Override
 public void iterPhys()
 {
  super.iterPhys();
  collisionDetector.calc();
 }
}
