package com.mygdx.game.ext.drawable.components;

public class Field<T>
{
 T field;

 public Field(T object) { field = object; }

 public T get()
 {
  return field;
 }
}
