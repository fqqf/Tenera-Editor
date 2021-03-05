package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class DrawingComponent extends Component<DrawingComponent>
{
 private static final ComputeableHashMap<DrawingComponent> childList = new ComputeableHashMap<>();

 public Texture texture;

 public DrawingComponent(Texture texture, Actor actor)
 {
  super(actor);
  this.texture = texture;
 }

 public static DrawingComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new DrawingComponent(new Texture("box.png"), actor));
 }
}