package com.example.pkijanski.firstgame.gameobjects.moveactions;

import android.graphics.Point;

/**
 * Created by deamo on 11.03.2018.
 */

public interface Moveable {


    public void update();
    public void moveTo(Point point);
    public void setPosition(Point point);
    public void stopMoving();

    public Point getCurrentPosition();
}
