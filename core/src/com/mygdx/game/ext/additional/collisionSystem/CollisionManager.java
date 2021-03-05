package com.mygdx.game.ext.additional.collisionSystem;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.component.Field;
import com.mygdx.game.ext.core.group.Group;

public class CollisionManager
{
    Group actors = new Group(); // Due to be managed by another class

    public void calc() // Calculating all actors collisions one by one
    {
        for (int i = 0; i < actors.size; i++)
        {
            Actor actorA = actors.get(i);
            boxA = getBox(actorA);
            for (Actor actorB : actors)
            {
                if (actorA == actorB) continue;
                boxB = getBox(actorB);
                if (boxA.overlaps(boxB)) handleCollision(actorA, actorB);
            }
        }
    }

    BoundingBox boxA, boxB;

    // TODO: В этом методе находится switch-case, который определяет по типу двух коллизий,
    //  куда их отправить, к примеру body-body -> handleBodyBody();
    //  (т.е, все как расписано в ComponentType
    //  Тебе нужно реализовать простейшую bodySolid, где body выталкивает вектор из solid
    private void handleCollision(Actor actorA, Actor actorB)
    {
        int typeA = getBox(actorA).getType();
        if (typeA != CollisionType.BODY) return;
        System.out.println("collision detect!");
        int typeB = getBox(actorB).getType();
        switch (typeB)
        {
            case CollisionType.SOLID:
                handleBodySolid(actorA, actorB);
                break;
            case CollisionType.LIQUID:
                handleBodyLiquid(actorA, actorB);
                break;
        }

    }

    private final Vector2 centerA = new Vector2(), centerB = new Vector2();

    private void handleBodyBody() {}

    private void handleBodySolid(Actor actorBody, Actor actorSolid)
    {
        boxA = getBox(actorBody);
        boxB = getBox(actorSolid);
        boxA.getCenter(centerA);
        boxB.getCenter(centerB);

        float deltaX = centerA.x - centerB.x;
        float deltaY = centerA.y - centerB.y;

        if (Math.abs(deltaX) < Math.abs(deltaY))
        {
            getV2(actorBody, "position").y += deltaY * 0.1f;// * Math.abs(getV2(actorBody,"velocity").y);
            getV2(actorBody, "velocity").y *= 0;
        }
        else
        {
            getV2(actorBody, "position").x += deltaX * 0.1f;// * Math.abs(getV2(actorBody,"velocity").x);
            getV2(actorBody, "velocity").x *= 0;
        }

        // if (Math.abs( deltaX ) < Math.abs( deltaY )) getV2(actorBody,"velocity").x = deltaX/5;
        // else getV2(actorBody,"velocity").y = deltaY/5;
    }

    private void handleBodyLiquid(Actor actorBody, Actor actorLiquid)
    {
        getV2(actorBody, "velocity").scl(0.5f, 0.5f);
    }

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
        actor.computeField("box", () -> new Field<>(new BoundingBox(CollisionType.NONE)));

        BoundingBox box = getBox(actor);

        if (box.height == 0 || box.width == 0)
            box.set(getV2("position"), getV2("size")); // If no bounding was set, gets it from assigned actor;

    }

    private Actor actor; // For use between methods

    @SuppressWarnings("unchecked")
    private Vector2 getV2(String string)
    {
        Field<Vector2> vector2;
        return (vector2 = actor.getField(string, true)).get();
    }

    @SuppressWarnings("unchecked")
    private Vector2 getV2(Actor actor, String string)
    {
        Field<Vector2> vector2;
        return (vector2 = actor.getField(string, true)).get();
    }
}
