package com.mygdx.game.test1;

import com.mygdx.game.ext.scene.StandartActor;

public class Hero extends StandartActor
{

 public Hero()
 {
  super();
 }

 @Override
 public void behave()
 {
  super.behave();
  speed.x +=0.01;
 }
}
