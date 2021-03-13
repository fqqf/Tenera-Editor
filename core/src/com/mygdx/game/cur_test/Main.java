package com.mygdx.game.cur_test;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.cur_test.Locations.StickmanWorld;
import com.mygdx.game.ext.core.data.AssetLoadScene;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.scene.Scene;

public class Main extends ApplicationLoop
{
 // TODO : Система не проверяет, есть ли у актера n-ый компонент. Система проходится по своим внутренним актерам.
 //  Если у внутреннего актера системы нет компонента, он будет добавлен

 Scene scene;

 @Override
 public void create()
 {
  super.create();
  AssetManager assetManager = new AssetManager();
  assetManager.load("test7/grass.png", Texture.class);
  assetManager.load("test7/long_grass.png", Texture.class);
  assetManager.load("test7/castle.png", Texture.class);
  assetManager.load("test7/platform.png", Texture.class);
  assetManager.load("test7/hero.png", Texture.class);

  scene = new AssetLoadScene( "super-location",new ExtendCoordinateGrid("coordinate-grid", 1),10,10 )
          .assetManager( assetManager )
          .setActionOnLoaded(
                  arg ->
                  {
                   System.out.println("loaded!");
                   assetManager.dispose();
                   scene = new StickmanWorld("super-location",new ExtendCoordinateGrid("coordinate-grid", 12),100,100);
                  })
          .ready();

  // scene = new StickmanWorld("super-location",new ExtendCoordinateGrid("coordinate-grid", 12),100,100);
 }

 @Override
 public void drawGraphics()
 {
  scene.iterDraw(extrapolation);
 }

// @Override
// public void handleInput()
// {
//  stickmanWorld.iterInput();
// }

 @Override
 public void calcPhysics() { scene.iterPhys(); }
}
