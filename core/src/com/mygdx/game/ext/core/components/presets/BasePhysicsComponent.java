package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class BasePhysicsComponent extends Component<BasePhysicsComponent>
{
 private static final ComputeableHashMap<BasePhysicsComponent> childList = new ComputeableHashMap<>();

 public Vector2 position = new Vector2();
 public Vector2 size = new Vector2();
 public Vector2 velocity = new Vector2();

 public BasePhysicsComponent(Actor actor)
 {
  super(actor);
 }

 public static BasePhysicsComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new BasePhysicsComponent(actor));
 }
}