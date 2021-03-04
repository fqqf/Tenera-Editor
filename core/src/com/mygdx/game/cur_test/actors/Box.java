package com.mygdx.game.cur_test.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.cur_test.components.MyComponents;
import com.mygdx.game.ext.additional.collisionSystem.CollisionType;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;

public class Box extends Actor
{
 public Box()
 {
  addComp(MyComponents.drawing);

  vector2 = getField("position"); vector2.get().x = 1;

  vector2 = getField("size"); vector2.get().x = 3; vector2.get().y = 3;

  addField("texture", new Field<>(new Texture("box.png")));

  addField("box", new Field<>(CollisionType.SOLID));
 }

 Field<Vector2> vector2;
}
