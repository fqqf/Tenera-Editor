package com.mygdx.game.cur_test.Entities;

import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.components.presets.ActionComponent;
import com.mygdx.game.ext.core.scene.presets.GameScene;

public class Worker extends Actor
{
	public Worker(Action.Arg1<Actor> action)
	{
		ActionComponent.get(this).action = action;
		GameScene.actionSystem.addActor(this);
	}
}
