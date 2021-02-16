package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.scene.StandartActor;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.scene.StandartScene;

public class DarkForest extends StandartScene
{
 Texture l1, l2, l3, back;

 public DarkForest(String name, ExtendField field, float width, float height)
 {
  super(name, field, width, height);

  l1 = new Texture("1.png"); l2 = new Texture("2.png");
  l3 = new Texture("3.png"); back = new Texture("back.png");

  new StandartActor().size(74,9).position(0,0).texture(l3).scene(this, "l3").setDrawLayer(10);
  new StandartActor().size(100,25).position(0,0).texture(back).scene(this, "back").setDrawLayer(0);
  new StandartActor().size(90,14).position(0,0).texture(l2).scene(this, "l1").setDrawLayer(4);
  new StandartActor().size(100,20).position(0,0).texture(l1).scene(this, "l2").setDrawLayer(7);

  // TODO : PARALLAX EFFECT
  // TODO : EVENT
  // TODO : MOVE HERO
 }
}
