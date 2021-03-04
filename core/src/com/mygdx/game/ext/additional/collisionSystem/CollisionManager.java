package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.group.Group;

public class CollisionManager
{
	Group actors = new Group(); // Due to be managed by another class

	public void calc() // Calculating all actors collisions one by one
	{
		for (Actor actorA : actors)
		{
			boxA = getBox(actorA);
			for (Actor actorB : actors)
			{
				boxB = getBox(actorB);
				if (actorA == actorB) continue;
				if (boxA.overlaps(boxB)) handleCollision();
			}
		}
	}

	BoundingBox boxA, boxB;

	// TODO: В этом методе находится switch-case, который определяет по типу двух коллизий,
	//  куда их отправить, к примеру body-body -> handleBodyBody();
	//  (т.е, все как расписано в ComponentType
	//  Тебе нужно реализовать простейшую bodySolid, где body выталкивает вектор из solid
	private void handleCollision()
	{

	}

	private void handleBodyBody(){}
	private void handleBodySolid(){}
	private void handleBodyLiquid(){}

	Field<BoundingBox> rectangleField;

	@SuppressWarnings("unchecked")
	private BoundingBox getBox(Actor actor)
	{
		rectangleField = actor.getField("box");
		return rectangleField.get();
	}

	public void addActor(Actor... actors)
	{
		for (Actor actor : actors) { loadFields(actor); }
		this.actors.addAll(actors);
	}

	private void loadFields(Actor actor)
	{
		this.actor = actor;

		actor.computeField("position", () -> new Field<>(new Vector2(0, 0)));
		actor.computeField("size", () -> new Field<>(new Vector2(0, 0)));
		actor.computeField("box", () -> new Field<>(CollisionType.NONE));

		BoundingBox box = getBox(actor);

		if (box.height==0 || box.width==0) box.set(getV2("position"), getV2("size")); // If no bounding was set, gets it from assigned actor;
	}

	private Actor actor; // For use between methods

	@SuppressWarnings("unchecked")
	private Vector2 getV2(String string)
	{
		Field<Vector2> vector2;
		return (vector2 = actor.getField(string, true)).get();
	}
}
