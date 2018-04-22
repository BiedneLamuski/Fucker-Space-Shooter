package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Align;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveToDirection extends MoveToAction{

    private final int velocity;
    private double direction;


    public MoveToDirection(int velocity)
    {
        this.velocity = velocity;
    }

    @Override
    public boolean act(float delta) {
        float x_inc = (float) (delta*velocity * Math.cos(direction));
        float y_inc = (float) (delta*velocity * Math.sin(direction));

        target.setPosition(target.getX() + x_inc, target.getY() + y_inc);
        return false;
    }

    @Override
    public void setPosition(float x, float y) {
        direction = Math.atan2(y, x);
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        direction = Math.atan2(y, x);
    }
}
