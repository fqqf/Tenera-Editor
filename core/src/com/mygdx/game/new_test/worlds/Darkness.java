package com.mygdx.game.new_test.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ext.core.components.presets.BasePhysicsComponent;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.new_test.Systems;
import com.mygdx.game.new_test.creatures.Hero;
import com.mygdx.game.new_test.environment.Ground;
import com.mygdx.game.new_test.environment.Platform;

public class Darkness extends Scene
{
 Group drawLayer = new Group();

 public Darkness(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);

  addSystem(
    Systems.collisionManagmentSystem, Systems.physicsSystem,
    Systems.controlSystem, Systems.drawingSystem,
    Systems.animationSystem
  );

  drawLayer.add(
          new Hero(2,5),
          new Ground(0,0),
          new Ground(38.22f,0)
  //        new Npc(15,0)
  );//new Wind(3,2));
  Platform platform = new Platform(2,4);
  drawLayer.add(
   //       new Wind(3,2),
  //        new Gem(7,5),
          platform,
          new Platform(BasePhysicsComponent.get(platform).position.x + BasePhysicsComponent.get(platform).size.x - CollisionComponent.get(platform).box.x,4)
  );

  Systems.drawingSystem.layers.put(1, drawLayer);
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
