package com.mygdx.game.new_game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ext.core.actor.Actor;
import com.mygdx.game.ext.core.components.presets.CollisionComponent;
import com.mygdx.game.ext.core.components.presets.DrawingComponent;
import com.mygdx.game.ext.core.drawing.ApplicationLoop;
import com.mygdx.game.ext.core.drawing.view.CoordinateGrid;
import com.mygdx.game.ext.core.drawing.view.ExtendCoordinateGrid;
import com.mygdx.game.ext.core.event.Event;
import com.mygdx.game.ext.core.group.presets.Layer;
import com.mygdx.game.ext.core.scene.Scene;
import com.mygdx.game.ext.core.system.System;
import com.mygdx.game.ext.core.system.presets.DrawingSystem;
import com.mygdx.game.ext.core.system.presets.collisionSystem.CollisionSystem;
import com.mygdx.game.new_game.SpriteManager;
import com.mygdx.game.new_game.Systems;
import com.mygdx.game.new_game.drawing.entities.Alice;
import com.mygdx.game.new_game.drawing.InvisibleWall;
import com.mygdx.game.new_game.drawing.background.Background;
import com.mygdx.game.new_game.drawing.cutscene.CutsceneImage;
import com.mygdx.game.new_game.drawing.cutscene.CutsceneText;
import com.mygdx.game.new_game.drawing.entities.Ghost;
import com.mygdx.game.new_game.drawing.stat.*;
import com.mygdx.game.new_game.events.SpawnGear;
import com.mygdx.game.new_game.events.SpawnGhost;
import com.mygdx.game.new_game.events.cutscenes.Cutscene;
import com.mygdx.game.new_game.events.cutscenes.Greeting;

import java.lang.reflect.InvocationTargetException;

public class FirstAliceLevel extends Scene
{
 public static CutsceneImage cutsceneImage, cutsceneBackground;
 public static CutsceneText cutsceneText;

 public static boolean gameOver = false;
 private static final Array<Layer> allLayer = new Array<>();

 public static Layer npc = new Layer(null);
 public static Layer alicel = new Layer(null);
 public static Layer background = new Layer(null);
 public static Layer effects = new Layer(null);
 public static Layer grass = new Layer(null);
 public static Layer cutscene = new Layer(null);
 public static Layer cutscene_b = new Layer(null);
 public static Layer environment = new Layer(null);
 public static Layer events = new Layer(null);
 public static Layer interfaceL = new Layer(null);

 static
 { // это такая мега некрасивая архиектура сейчас
  allLayer.add(npc);allLayer.add(alicel);allLayer.add(background);allLayer.add(effects);allLayer.add(grass);
  allLayer.add(cutscene);allLayer.add(environment);allLayer.add(events);allLayer.add(interfaceL); allLayer.add(cutscene_b);
 }

 private final BitmapFont bitmapFont = new BitmapFont();
 private Alice alice;
 public FirstAliceLevel(String name, CoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);
  setSceneSystems();
  configureLayers();
  DrawingSystem.DEBUG = true;
  Systems.keyBoardSystem.cameraController = field.cameraController;
  Systems.drawingSystem.cameraController = field.cameraController;

  loadResource();


 }

 private void loadResource()
 {
  SpriteManager.asset.load("hitobashira_demo/atlas/atlas.txt", TextureAtlas.class);
 }


 @Override
 public void iterDraw(float extrapolation)
 {
  Gdx.gl.glClearColor(0.32156f, 0.32156f, 0.32156f, 1);
  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

  if (SpriteManager.asset.isLoaded)
  {
   drawGrid();
   super.iterDraw(extrapolation);
  }
  else if (SpriteManager.asset.update())
  {
   alice = new Alice(()->{gameOver = true;});
   initScene();
  }
  else
   {
    batch.begin();
    bitmapFont.draw(batch,"Loading... " + (int)(SpriteManager.asset.getProgress()*100) +"%", 600,300);
    batch.end();
   }
  if (gameOver)
  {
   ApplicationLoop.logger.info("GAME OVER");
   initScene();
  }
 }

 private void clearScene()
 {

  Array<System> systems = getSystems();

  for (Layer layer : allLayer)
  {
   for (System system : systems)
   {
    system.remActor(layer);
   }
   layer.clear();
  }
  CollisionSystem.world.reset();
 }

 private void initScene()
 {
  gameOver = false;
  clearScene();
  alice.init(7,5);
  alice.initHearts(4);
  CollisionSystem.world.update(CollisionComponent.get(alice).item,3,5);

  alicel.add(alice);

  grass.add(
          new InvisibleWall(0,0,1000,0.1f),
          new InvisibleWall(0,0,0.1f,15),
          new InvisibleWall(600,0,0.1f,15)
  );

  for (int i = 0; i < 60; i++) grass.add(new Grass(i*11.73f,0));

  background.add(new Background());

  generateEnvironment();


  VignetteRect vignette = new VignetteRect(0,0);
  VignetteRect vignetteTop = new VignetteRect(0,12-7.11f);
  DrawingComponent.get(vignette).spriteColor.set(0.54f,0.196f,0.196f,1);
  DrawingComponent.get(vignette).flipY = true;
  DrawingComponent.get(vignetteTop).spriteColor.set(0,0,0,0.7f);

  background.add(vignette);
  effects.add(vignetteTop);
  cutscene.add(cutsceneImage=new CutsceneImage(), cutsceneText=new CutsceneText());
  cutscene_b.add(cutsceneBackground=new CutsceneImage());
  alicel.add(alice.swordBox);
  Cutscene.set(cutsceneImage, cutsceneBackground, cutsceneText);

  Systems.drawingSystem.setMaster(alice);

  Systems.eventSystem.reset();
  Systems.eventSystem.setMaster(alice).addEvent(new SpawnGear(10,0),new SpawnGear(40,0), new SpawnGear(20,0), new Greeting(7,5));
  SpawnGhost sp = new SpawnGhost(-10,-10);
  sp.play();
 }


 private Class<? extends Actor>[] environments =
         new Class[]{
                 Cross.class,
                 Stump.class,
                 TreeA.class,
                 TreeC.class,
                 TreeB.class};
 private <T extends Actor> T create(Class<T> type, float x, float y)
 {
  T actor;
  try
  {
   actor = type.getConstructor(float.class, float.class).newInstance(x,y);
  }
  catch (InstantiationException  | NoSuchMethodException | InvocationTargetException | IllegalAccessException e)
  {
   e.printStackTrace();
   throw new Error();
  }
  return actor;
 }

 private void generateEnvironment()
 {
  for (int i = 1; i < 10; i++)
  {
   environment.addAll(
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(0,20) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(0,20) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(20,40) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(20,40) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(40,60) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(40,60) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(60,70) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(60,70) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(70,80) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(70,80) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(90,95) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(90,95) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(80,100) * i,0),
           create( environments[MathUtils.random(0,environments.length-1)], MathUtils.random(80,100) * i,0)
   );
//   environment.addAll(
//           create(Poet.class, 20,0),
//           new Cross(60 * i,0),
//           new Tower(100 * i,0),
//           new Stump(68 * i,0),
//           new TreeA(74 * i,0),
//           new Haunted(83 * i,0),
//           new TreeC(90 * i,0),
//           new TreeB(95 * i,0)
//   );
  }
 }

 private void configureLayers()
 {
  CoordinateGrid cameraGrid = new ExtendCoordinateGrid("camera-grid",12);
  npc.setCoordinateGrid(field);
  background.setCoordinateGrid(cameraGrid);
  effects.setCoordinateGrid(cameraGrid);
  grass.setCoordinateGrid(field);
  alicel.setCoordinateGrid(field);
  cutscene.setCoordinateGrid(cameraGrid);
  environment.setCoordinateGrid(field);
  events.setCoordinateGrid(field);
  interfaceL.setCoordinateGrid(cameraGrid);
  cutscene_b.setCoordinateGrid(cameraGrid);


  Systems.drawingSystem.layers.put(4, background);
  Systems.drawingSystem.layers.put(5, environment);
  Systems.drawingSystem.layers.put(6, npc);
  Systems.drawingSystem.layers.put(7, alicel);
  Systems.drawingSystem.layers.put(8, grass);
  Systems.drawingSystem.layers.put(9, effects);
  Systems.drawingSystem.layers.put(10, interfaceL);
  Systems.drawingSystem.layers.put(11, cutscene_b);
  Systems.drawingSystem.layers.put(12, cutscene);

  Systems.eventSystem.setLayer(events);
 }

 private void setSceneSystems()
 {

  addSystem(System.Type.RENDER_SYSTEM,
    Systems.keyBoardSystem,
    Systems.cutsceneKeyBoardSystem,
    Systems.animationSystem,
    Systems.drawingSystem
  );
  addSystem(System.Type.PHYSICS_SYSTEM,
    Systems.aliceBehaviourSystem,
    Systems.physicsSystem,
    Systems.eventSystem,
    // Systems.gravitySystem,
    Systems.collisionSystem
  );
 }

 @Override
 public void iterPhys()
 {
  super.iterPhys();
 }
}
