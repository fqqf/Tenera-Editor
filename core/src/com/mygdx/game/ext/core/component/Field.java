package com.mygdx.game.ext.core.component;

public class Field<INSTANCE_TYPE>
{
 public INSTANCE_TYPE instance;

 public Field(INSTANCE_TYPE object)
 {
  instance = object;
 }

 @SuppressWarnings("unchecked")
 public INSTANCE_TYPE get()
 {
  return instance;
 }
}
