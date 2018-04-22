package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Align;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveToFinger extends MoveToAction implements MoveAction {

    private final int velocity;


    public MoveToFinger(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public boolean act(float delta) {
        float delta_x = getX() - target.getX();
        float delta_y = getY() - target.getY();
        double direction = Math.atan2(delta_y, delta_x); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions
        float x_inc = (float) (delta * velocity * Math.cos(direction));
        float y_inc = (float) (delta * velocity * Math.sin(direction));

        target.setPosition(target.getX() + x_inc, target.getY() + y_inc);

        if (target.getX() == getX() && target.getY() == getY()) {
            return true;
        } else {
            return false;
        }
    }

    public void moveTo(float x, float y) {
        System.out.println("Moving to point: " + x + "." + y);
        setPosition(x, y, Align.center);
    }
}