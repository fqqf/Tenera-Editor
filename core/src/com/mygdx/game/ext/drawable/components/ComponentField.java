package com.mygdx.game.ext.drawable.components;

public class ComponentField<T>
{
 T field;

 public ComponentField()
 {
  // TODO : Find better way to create instance of generic-type
  field = (T) new Object();
 }

 public T get()
 {
  return field;
 }
}
