package com.mygdx.game.ext;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.core.ExtendField;
import com.mygdx.game.ext.drawable.Scene;
import com.mygdx.game.ext.drawable.actors.BaseActor;
import com.mygdx.game.ext.drawable.components.ExtrapolationComponent;
import com.mygdx.game.ext.drawable.components.MovementComponent;

public class Cave extends Scene<Cave>
{
 BaseActor cube = new BaseActor(), table = new BaseActor();

 public Cave(String name, ExtendField field, float width, float height)
 {
  super(name, field, width, height);
  actor(cube, table);

  cube.texture(new Texture("cube.png")).size(4,4).position(3,0);
  table.texture(new Texture("table.png")).size(4,8).position(14,0);

  cube.component(new MovementComponent(), new ExtrapolationComponent());
 }


}
