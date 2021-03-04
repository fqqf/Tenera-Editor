package com.mygdx.game.cur_test.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.components.MyComponents;
import com.mygdx.game.ext.additional.collisionSystem.CollisionType;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;

public class Hero extends Actor
{
 public Hero()
 {
  addComp(MyComponents.extrapolationDrawing, MyComponents.movement, MyComponents.control);

  vector2 = getField("position"); vector2.get().x = 5;

  vector2 = getField("size"); vector2.get().x = 3; vector2.get().y = 3;

  addField("texture", new Field<>(new Texture("hero.png")));

  addField("box", new Field<>(CollisionType.BODY));
 }

 Field<Vector2> vector2;
}
