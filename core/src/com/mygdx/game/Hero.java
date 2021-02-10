package com.mygdx.game;

import com.mygdx.game.ext.Dobject;
import com.mygdx.game.ext.View;

public class Hero extends Dobject
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
