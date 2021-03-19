package com.mygdx.game.ext.core.components.presets;

import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.Item;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class CollisionComponent extends Component<CollisionComponent>
{
 private static final ComputeableHashMap<CollisionComponent> childList = new ComputeableHashMap<>();

 public final BoundingBox box;
 public final Item<Actor> item;
 public final Vector2 size = new Vector2(0,0);

 public CollisionComponent(Actor actor)
 {
  super(actor);
  item = new Item<>(actor);
  BasePhysicsComponent basePhysicsComponent = BasePhysicsComponent.get(actor);
  box = new BoundingBox(CollisionType.SOLID).set(basePhysicsComponent.position, basePhysicsComponent.size);
 }

 public static CollisionComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new CollisionComponent(actor));
 }
 public static  boolean has(Actor actor) {return  childList.containsKey(actor);}
}
