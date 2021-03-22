package com.mygdx.game.tests.new_test.worlds;
/*
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.components.presets.PhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.drawing.view.CoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.tests.new_test.Systems;
import com.mygdx.game.tests.new_test.creatures.Hero;
import com.mygdx.game.tests.new_test.environment.*;

public class Darkness extends Scene
{
 public Layer drawLayer, effectsLayer, interfaceLayer;

 public Darkness(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  addSystem(
    Systems.collisionSystem, Systems.physicsSystem,
    Systems.controlSystem, Systems.drawingSystem,
    Systems.animationSystem
  );

  drawLayer = new Layer(field);
  CoordinateGrid notMovingGrid = new ExtendCoordinateGrid("effect-coordinate-grid",10);
  effectsLayer = new Layer(notMovingGrid);
  interfaceLayer = new Layer(notMovingGrid);

  drawLayer.add(
          Systems.controlSystem.getHop(),
          new Hero(2,2),
          new Ground(0,0),
          new Ground(38.22f,0));

  Platform platform = new Platform(2,4);

  drawLayer.add( // TODO: A position, where we want bounding box to be
          platform,
          new Platform(PhysicsComponent.get(platform).position.x +
            PhysicsComponent.get(platform).size.x - CollisionComponent.get(platform).box.x,4)
  );

  effectsLayer.add(new Vignette(0,10-2.06f));
  interfaceLayer.add(new InvisibilityButton(notMovingGrid.unitWidth-10f,notMovingGrid.unitHeight-9.5f));

  Systems.drawingSystem.layers.put(1, drawLayer);
  Systems.drawingSystem.layers.put(2, effectsLayer);
  Systems.drawingSystem.layers.put(3, interfaceLayer);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.32156f, 0.32156f, 0.32156f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  drawGrid();
  super.iterDraw(extrapolation);
 }
}
*/