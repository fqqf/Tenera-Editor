package com.mygdx.game.cur_test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.actors.Box;
import com.mygdx.game.cur_test.actors.Hero;
import com.mygdx.game.cur_test.actors.Water;
import com.mygdx.game.cur_test.components.MyComponents;
import com.mygdx.game.ext.additional.collisionSystem.CollisionManager;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.group.Group;
import com.mygdx.game.ext.core.scene.presets.GameScene;

public class DelightfulLocation extends GameScene
{
 public Group entitiesLayer = new Group();

 public DelightfulLocation(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);


  // for test
  Hero hero = new Hero();
  Field<Vector2> field1 = hero.getField("position");
  field1.get().set( 5,5 );
  hero.remComp(MyComponents.control);
  hero.remComp(MyComponents.movement);

  Hero hero1 = new Hero();
  Box box = new Box();
  Water water = new Water();
  collisionManager.addActor( hero1,hero,box,water);
  //

  entitiesLayer.addAll( hero1, water, box, hero );
  layers.put(1, entitiesLayer);
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  super.iterDraw(extrapolation);
 }
}
