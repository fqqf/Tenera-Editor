package com.mygdx.game.ext.core.components.presets;

import com.dongbat.jbump.Item;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.components.ComputeableHashMap;
import com.mygdx.game.ext.core.system.presets.collisionSystem.BoundingBox;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionType;

public class CollisionComponent extends Component
{
 private static final ComputeableHashMap<CollisionComponent> childList = new ComputeableHashMap<>();

 public boolean isStanding = false;
 //public final BoundingBox box;

 public final Item<Actor> item;
 public int collisionType = CollisionType.SOLID;
 public boolean inStanding = false;

 private CollisionComponent(Actor actor)
 {
  item = new Item<>(actor);
  BodyPropertiesComponent bodyPropertiesComponent = BodyPropertiesComponent.get(actor);
  //box = new BoundingBox(CollisionType.SOLID).set(bodyPropertiesComponent.position, bodyPropertiesComponent.size);
 }

 public static CollisionComponent get(Actor actor)
 {
  return childList.compute(actor, () -> new CollisionComponent(actor));
 }

 public static boolean contains(Actor actor)
 {
  return childList.containsKey(actor);
 }

 public static void remActor(Actor actor)
 {
  childList.remove(actor);
 }

 public static  boolean has(Actor actor) {return  childList.containsKey(actor);}
}
