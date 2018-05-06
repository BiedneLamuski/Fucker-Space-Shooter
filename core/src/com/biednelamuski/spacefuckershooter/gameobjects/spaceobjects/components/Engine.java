package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Engine extends SpaceObjectComponent {

    private final Class<MoveToAction> moveToAction;

    private final float thrust; //in newtons
    private final float reverseTrhust;
    private final float rotationThrust;



    public Engine(final float energyConsumption, float thrust, float reverseTrhust, float rotationThrust, Class<MoveToAction> moveToAction)
    {
        super(energyConsumption);
        this.thrust = thrust;
        this.reverseTrhust = reverseTrhust;
        this.rotationThrust = rotationThrust;
        this.moveToAction = moveToAction;
    }

    public MoveToAction getMoveToAction() {
        return moveToAction.newInstance();
    }
}
