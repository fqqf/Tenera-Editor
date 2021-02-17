package com.mygdx.game.ext.drawable.components;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.ApplicationLoop;
import com.mygdx.game.ext.drawable.Actor;
import com.mygdx.game.ext.drawable.Component;

public class ExtrapolationComponent extends Component<ExtrapolationComponent>
{
 ApplicationLoop<?> loop;

 public ExtrapolationComponent()
 {
  type = ComponentType.GRAPHICS_COMPONENT; // todo: remove comptype class
  loop = ApplicationLoop.instance;
 }

 ComponentField<Vector2> speed;

 @Override
 public void init(Actor<?> actor)
 {
  if (actor.componentValues.get("speed") == null)
   actor.componentValues.put("speed", new ComponentField<>(Vector2::new));
 }

 @Override @SuppressWarnings("unchecked")
 public void behave(Actor<?> actor)
 {
  speed = actor.componentValues.get("speed");

  actor.getDrawPosition().set(
    actor.position.x+speed.get().x*loop.extrapolation,
    actor.position.y+speed.get().y*loop.extrapolation
  );
 }
}
