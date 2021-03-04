package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Collections;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.group.Group;

import java.util.Collection;
import java.util.HashSet;

public class CollisionManager
{
	Group actors = new Group(); //managed by another class
  public void calc()
	{
		// go thru all actors and find collision
   for (Actor actor : actors)
		{
			for (Actor actor1 : actors)
			{
				if (actor == actor1) continue;
				if (checkCollision(actor, actor1)) checkCollision(actor,actor1);
			}
		}
	}

	public void addActor(Actor... actors)
	{
		for (Actor actor : actors)
		{
			actor.computeField("position", () -> new Field<>(new Vector2(0, 0)));
			actor.computeField("size", () -> new Field<>(new Vector2(0, 0)));
			actor.computeField("collision", () -> new Field<>(CollisionType.SOLID));

			Field<Vector2> position = actor.getField("position"), size = actor.getField("size");

			actor.computeField("AABBposition", () -> new Field<>(new Vector2(position.get())));
			actor.computeField("AABBsize", () -> new Field<>(new Vector2(size.get())));
		}
	}

	private final Rectangle rectangle1 = new Rectangle(), rectangle2 = new Rectangle();

	public boolean checkCollision(Actor actor1, Actor actor2)
	{
		setRectangleData(actor1, rectangle1);
		setRectangleData(actor2, rectangle2);
		return rectangle1.overlaps(rectangle2);
	}

	// TODO: В этом методе находится switch-case, который определяет по типу двух коллизий,
	//  куда их отправить, к примеру body-body -> handleBodyBody();
	//  (т.е, все как расписано в ComponentType
	//  Тебе нужно реализовать простейшую bodySolid, где body выталкивает вектор из solid
	private void handleCollision(Actor actor1, Actor actor2)
	{
		Vector2 speed1 = getSpeed(actor1);
		Vector2 speed2 = getSpeed(actor2);
		
	}

	private void handleBodyBody(){}
	private void handleBodySolid(){}
	private void handleBodyLiquid(){}

	private void setRectangleData(Actor actor, Rectangle rectangle)
	{
		Field<Vector2> position = actor.getField("AABBposition");
		Field<Vector2> size = actor.getField("AABBsize");
		rectangle.setPosition(position.get());
		rectangle.setSize(size.get().y, size.get().y);
	}

	private Vector2 getSpeed(Actor actor)
	{
		Field<Vector2> speedField = actor.getField("velocity");
		return speedField.get();
	}


}
