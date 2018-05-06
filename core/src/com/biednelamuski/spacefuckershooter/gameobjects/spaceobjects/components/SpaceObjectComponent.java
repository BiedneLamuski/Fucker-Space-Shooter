package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;

public class SpaceObjectComponent extends Actor {
    private SpaceObject owner;
    private final float energyConsumption;

    public SpaceObjectComponent(float energyConsumption)
    {
        this.energyConsumption = energyConsumption;
    }

    public SpaceObject getOwner() {
        return owner;
    }

    public void setOwner(SpaceObject owner) {
        this.owner = owner;
    }
}
