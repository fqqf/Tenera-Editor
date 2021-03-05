package com.mygdx.game.ext.core.actor;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ext.core.components.Component;
import com.mygdx.game.ext.core.drawing.view.Monitor;

import java.util.HashMap;

public abstract class Actor
{
 public boolean doDrawing = true, doActing = true, doInputHandling = true;  // Used by Scene to determine call Actor methods or not

 public void pause()
 {
  doDrawing = false;
  doActing = false;
  doInputHandling = false;
 }

 public void resume()
 {
  doDrawing = true;
  doActing = true;
  doInputHandling = true;
 }
}
