package com.mygdx.game.ext.drawable.components;

import com.mygdx.game.ext.core.Monitor;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class ComponentField<T>
{
 T field;

 public ComponentField(Supplier<T> supplier)
 {
  // TODO : Find better way to create instance of generic-type
  field = supplier.get();
 }

 public T get()
 {
  return field;
 }
}
