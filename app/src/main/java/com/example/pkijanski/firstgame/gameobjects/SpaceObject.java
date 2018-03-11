package com.example.pkijanski.firstgame.gameobjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.example.pkijanski.firstgame.gameobjects.moveactions.Moveable;

/**
 * Created by PKijanski on 06.03.2018.
 */


/**
 * Basic object.
 */
public class SpaceObject implements Moveable{
    final static int shooterWidth = 150;

    private Bitmap image;

    private Moveable moveType;

    public SpaceObject(Bitmap image, int startingX, int startingY, Moveable moveType) {

        int ratio = image.getWidth() / shooterWidth;
        this.image = Bitmap.createScaledBitmap(image, image.getWidth() / ratio, image.getHeight() / ratio, true);

        this.moveType = moveType;
        this.moveType.setPosition(new Point(startingX, startingY ));
    }

    public void draw(Canvas canvas) {

        Point currentPosition = moveType.getCurrentPosition();
        currentPosition.offset(-1*image.getWidth()/2, -1*image.getHeight()/2);
        canvas.drawBitmap(image, currentPosition.x, currentPosition.y, null);
    }

    public void update() {

        moveType.update();
    }

    @Override
    public void moveTo(Point point) {
//        point.offset(-1*(image.getWidth() / 2), -1*(image.getHeight() / 2));
        moveType.moveTo(point);
    }

    @Override
    public void setPosition(Point point) {

    }

    public void stopMoving() {
        moveType.stopMoving();
    }

    public Point getCurrentPosition() {
        return moveType.getCurrentPosition();
    }

    public String toString() {
        return image.toString();
    }
}
