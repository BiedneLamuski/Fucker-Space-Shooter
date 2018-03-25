package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Move object straight in a line
 */

//TODO: finish this class
public class MoveStraight implements Moveable {

    private final int velocity;
    private final double direction;
    private final GridPoint2 position;

    public MoveStraight(int velocity, int x, int y, double direction)
    {
         this.velocity = velocity;
         this.direction = direction;
         this.position = new GridPoint2(x, y);
//        double direction = Math.atan2(delta_y, delta_x); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions

    }

    public MoveStraight(int velocity, int x, int y, int destX, int destY)
    {
        this.velocity = velocity;
        this.position = new GridPoint2(x, y);
        this.direction = Math.atan2(destY - y, destX - x); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions

    }

    @Override
    public void update() {
        position.add((int)(velocity * Math.cos(direction)), (int)(velocity * Math.sin(direction)));
    }

    @Override
    public void moveTo(GridPoint2 point) {

    }

    @Override
    public void setPosition(GridPoint2 point) {

    }

    @Override
    public void stopMoving() {

    }

    @Override
    public GridPoint2 getCurrentPosition() {
        return new GridPoint2(position);
    }
}
