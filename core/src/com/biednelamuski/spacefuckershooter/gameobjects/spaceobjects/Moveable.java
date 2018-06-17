package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.biednelamuski.spacefuckershooter.moveactions.MoveAction;

/**
 * Define if object is moveable or not and provide movement params
 */
public interface Moveable {

    float getAccelerationSpeed();
    float getBreakingSpeed();
    float getRotationSpeed();
    void move(MoveAction moveAction);
    Body getBody();
}
