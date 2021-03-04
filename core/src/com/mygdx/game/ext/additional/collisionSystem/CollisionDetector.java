package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;

/**
 * Detect Collision
 */
public class CollisionDetector
{
	private Rectangle rectangle1 = new Rectangle(), rectangle2 = new Rectangle();
	public void checkCollision(Actor actor1, Actor actor2)
	{
		setRectangleData( actor1, rectangle1 );
		setRectangleData( actor2, rectangle2 );
		if ( rectangle1.overlaps( rectangle2 ))
		{

		}
	}

	private void setRectangleData(Actor actor, Rectangle rectangle)
	{
		Field<Vector2> position = actor.getField("position");
		Field<Vector2> size = actor.getField("size");
		rectangle.setPosition( position.get() );
		rectangle.setSize( size.get().y, size.get().y );
	}

}
