package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.drawable.actors.Actor;
import com.mygdx.game.ext.drawable.components.Field;

// TODO: Перенести реализацию ProActor в scene
public class ProActor extends Actor
{
 public void texture(String path)
 {
  this.addField("texture", new Field<>(new Texture(path)));
 }


 Field<Vector2> vector2;
 public Vector2 getV2(String name)
 {
  vector2 = getField(name, true);
  return vector2.get();
 }
}
