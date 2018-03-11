package com.example.pkijanski.firstgame.gameobjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.ImageView;

import com.example.pkijanski.firstgame.gameobjects.moveactions.Moveable;

/**
 * Created by PKijanski on 07.03.2018.
 */

public class MoveUp implements Moveable{

    private int yVelocity;

    private final static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Point position;

    public MoveUp(int Yvelocity) {
        this.yVelocity = Yvelocity;
    }

    public void update() {
            position.y -= yVelocity;

    }

    @Override
    public void moveTo(Point point) {

    }

    @Override
    public void setPosition(Point point) {
        this.position = point;
    }

    @Override
    public void stopMoving() {
    }


    @Override
    public Point getCurrentPosition() {
        //New point so that old one won't be overridden accidentally.
        return new Point(position);
    }
}
