package com.mygdx.game.ext.core.data;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.ext.core.actor.interfaces.Action;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

public class AssetLoadScene extends Scene
{ //todo не хочу по 100 раз переписывать xD не зная как именно ты хочешь это имплементировать xD мож ты систему хочешь... хотя смысл
	private Action.Arg1<AssetManager> onLoaded;
	private AssetManager assetManager;
	private AssetLoader assetLoader;
	private boolean ready;
	public AssetLoadScene(String name, ExtendCoordinateGrid field, float width, float height)
	{
		super(name, field, width, height);
	}
	public AssetLoadScene assetManager(final AssetManager assetManager)
	{
		this.assetManager = assetManager;
		return this;
	}
	public AssetLoadScene setActionOnLoaded(final Action.Arg1<AssetManager> onLoaded)
	{
		this.onLoaded = onLoaded;
		return this;
	}

	public AssetLoadScene ready()
	{
		if (assetManager==null)throw new Error("Not ready! assetManager is null!");
		if (onLoaded==null)throw new Error("Not ready! onLoaded is null!");
		assetLoader = new AssetLoader( assetManager,this::onLoad, onLoaded );
		ready = true;
		return this;
	}

	private void onLoad(AssetLoader.Data data)
	{
		text = "loaded..." + data.loadedPercent+"%";
		System.out.println(text);
	}
	private String text = "loading...";
	private final BitmapFont bitmapFont = new BitmapFont();
	@Override
	public void iterDraw(float extrapolation)
	{
		if (ready)
		{
			assetLoader.update();
			batch.begin();
			bitmapFont.draw( batch, text, width/2, height/2);
			batch.end();
		}
	}
}
