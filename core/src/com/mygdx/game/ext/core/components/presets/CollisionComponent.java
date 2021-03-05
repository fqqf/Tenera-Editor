package com.mygdx.game.ext.core.components.presets;

import com.mygdx.game.ext.core.components.presets.collisionSystem.BoundingBox;
import com.mygdx.game.ext.core.components.presets.collisionSystem.CollisionType;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;

public class CollisionComponent extends Component<CollisionComponent>
{
 private static final ComputeableHashMap<CollisionComponent> childList = new ComputeableHashMap<>();

 public BoundingBox box;

 public CollisionComponent(Actor actor)
 {
  super(actor);

  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);

  box = new BoundingBox(CollisionType.SOLID).set(basePhysicsComponent.position, basePhysicsComponent.size);
 }

 public static CollisionComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new CollisionComponent(actor));
 }
}
