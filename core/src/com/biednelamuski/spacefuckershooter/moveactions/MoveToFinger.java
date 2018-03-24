package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveToFinger implements Moveable {

    private boolean isMoving = false;
    private int x, y;
    private int destX, destY;

    private final int velocity;

    private final static int MOVE_THRESHOLD = 30;


    public MoveToFinger(int velocity)
    {
        this.velocity = velocity;
    }

    @Override
    public void update() {
        if(isMoving && Math.abs(x - destX) < MOVE_THRESHOLD && Math.abs(y - destY) < MOVE_THRESHOLD) {
            isMoving = false;
        }
        if(isMoving && x != destX && y != destY) {
            int delta_x = destX - x;
            int delta_y = destY - y;
            double direction = Math.atan2(delta_y, delta_x); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions
            double x_inc = (velocity * Math.cos(direction));
            double y_inc = (velocity * Math.sin(direction));
            x += x_inc;
            y += y_inc;
        }
    }


    @Override
    public void stopMoving() {
        isMoving = false;
    }

    @Override
    public GridPoint2 getCurrentPosition() {
        return new GridPoint2(x, y);
    }

    public void moveTo(GridPoint2 point) {
        if (null != point) {
            System.out.println("Moving to point: " + point.x + "." + point.y);
            isMoving = true;
            destX = point.x;
            destY = point.y + 150;
        }
    }

    @Override
    public void setPosition(GridPoint2 point) {
        System.out.println("Set position" + point.x + "." + point.y);
        x = point.x;
        y = point.y;
    }


}
