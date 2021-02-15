package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.Dobject;
import com.mygdx.game.ext.Field;
import com.mygdx.game.ext.Scene;

public class DarkVillageLevel extends Scene
{
 Texture red,blue,purple;

 public DarkVillageLevel(String name, Field field, float width, float height)
 {
  super(name, field, width, height);
  red = new Texture("red.png"); blue = new Texture("blue.png"); purple = new Texture("purple.png");

  // TODO: Реализация с пересортировкой всего при создании нового объекта очень сильно жрет. (особенно если прямо во время игры создается объект)
  //  Collections.sort() ( ПРИДУМАТЬ МЕТОД БЕЗ ПОСТОЯННОЙ СОРТИРОВКИ )
  //  +Избавиться от второй коллекции, сделать только одну.
  //  +Добавить метод addObjectS, чтобы можно было одним массивом добавить один лист объектов
  //  +Избавиться от идеи того, что имена объектов хранятся в самой сцене, пусть у каждого объекта
  //  будет поле name у самого объекта, а для получения объекта через getObject(name)
  //  проходить по каждому объекту в листе сцены, получая его значение name и сравнивая с данным
  //  , Map получается в сцене будет не нужен, удалить =)

  new Dobject().size(5,1).position(0,0).texture(red).scene(this, "red");
  new Dobject().size(4,1).position(0,0).texture(blue).scene(this, "blue");
  new Dobject().size(2,1).position(0,0).texture(purple).scene(this, "purple");

  // TODO: опять же, все что писала выше, убрать сортировку, добавить нормальный метод, итд итп
  this.listDobjectsByName.get("red").setDrawLayer(1);
  this.listDobjectsByName.get("blue").setDrawLayer(0);
  this.listDobjectsByName.get("purple").setDrawLayer(2);
  // TODO: + Как оказалось, система слоев вообще не работает после добавления их на сцену, т.к resort делается
  //  только после вызова addObject. Но опять же, тут всю систему переписывать надо, и от метода сортировки
  //  вообще избавиться, в остальном вроде бы все верно ;)

 }

 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  super.iterDraw(extrapolation);
 }
}
