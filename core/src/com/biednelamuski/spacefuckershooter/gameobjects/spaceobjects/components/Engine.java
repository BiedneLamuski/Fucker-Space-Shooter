package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Engine extends SpaceObjectComponent {

    private final float thrust; //in newtons
    private final float reverseTrhust;
    private final float rotationThrust;



    public Engine(final float energyConsumption, float thrust, float reverseTrhust, float rotationThrust)
    {
        super(energyConsumption);
        this.thrust = thrust;
        this.reverseTrhust = reverseTrhust;
        this.rotationThrust = rotationThrust;
    }

    public float getThrust() {
        return thrust;
    }

    public float getReverseTrhust() {
        return reverseTrhust;
    }

    public float getRotationThrust() {
        return rotationThrust;
    }
}
