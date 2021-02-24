package com.mygdx.game.ext;

import com.mygdx.game.ext.core.ExtendCoordinateGrid;
import com.mygdx.game.ext.drawable.components.Component;
import com.mygdx.game.ext.drawable.components.presets.DrawingComponent;
import com.mygdx.game.ext.drawable.components.presets.MovementComponent;
import com.mygdx.game.ext.drawable.groups.Group;
import com.mygdx.game.ext.drawable.scenes.presets.GroupLayerScene;

public class City extends GroupLayerScene
{
 ProActor cube1 = new ProActor(), cube2 = new ProActor(), platform = new ProActor();

 Group buildings = new Group(), players = new Group();

 public City(String name, ExtendCoordinateGrid field, float width, float height)
 {
  super(name, field, width, height);


  initActors();
  loadActorTextures();

  setActorsProperties();

  setLayers();
 }

 @Override
 public void iterDraw(float extrapolation)
 {
  super.iterDraw(extrapolation);

  drawGrid();
 }

 private void initActors()
 {
  Component<DrawingComponent> drawing = new DrawingComponent();

  addActor(cube1, cube2, platform);
  actors.forEach(actor -> actor.addComp(drawing));
  cube1.addComp(new MovementComponent());
 }

 private void loadActorTextures()
 {
  cube1.texture("cube1.png");
  cube2.texture("cube2.png");
  platform.texture("platform.png");
 }

 private void setActorsProperties()
 {
  cube1.getV2("position").set(0,0); cube2.getV2("position").set(10,0);
  cube1.getV2("size").set(2,2); cube2.getV2("size").set(3.5f, 3.5f);

  platform.getV2("position").set(10.6f,1);
  platform.getV2("size").set(7.44f, 1);
 }

 private void setLayers()
 {
  buildings.add(platform);
  players.addAll(cube1, cube2);

  layers.put(2,buildings);
  layers.put(0, players);
 }
}
