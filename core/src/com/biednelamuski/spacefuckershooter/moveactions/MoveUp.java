package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveUp implements Moveable{

    private int yVelocity;

    private GridPoint2 position;

    public MoveUp(int Yvelocity) {
        this.yVelocity = Yvelocity;
    }

    public void update() {
        position.y += yVelocity;

    }

    @Override
    public void moveTo(GridPoint2 point) {

    }

    @Override
    public void setPosition(GridPoint2 point) {
        this.position = point;
    }

    @Override
    public void stopMoving() {
    }


    @Override
    public GridPoint2 getCurrentPosition() {
        //New point so that old one won't be overridden accidentally.
        return new GridPoint2(position);
    }
}
