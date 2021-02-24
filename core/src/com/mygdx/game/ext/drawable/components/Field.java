package com.mygdx.game.ext.drawable.components;

public class Field<INSTANCE_TYPE>
{
 public INSTANCE_TYPE instance;

 public Field(INSTANCE_TYPE object)
 {
  instance = object;
 }

 public INSTANCE_TYPE get()
 {
  return instance;
 }
}
