package com.mygdx.game.ext.scene;

import java.util.ArrayList;

public abstract class Scene
{
 ArrayList<Group> layers; // Слои, отрисовываются по группам (упорядоченно)
 ArrayList<Group> dobjects; // Все объекты на сцене
}
