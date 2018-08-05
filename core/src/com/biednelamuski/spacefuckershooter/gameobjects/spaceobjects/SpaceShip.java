package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components.Engine;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components.SpaceObjectComponent;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components.Weapon;

import java.util.Collection;
import java.util.HashSet;

public class SpaceShip extends SpaceObject{


    /**
     * Replace maybe with WeaponSlot to which weapons are added? It would have size etc.
     */
    private int weaponSlotsNumber;

    private Engine engine = null;
    private Collection<Weapon> weapons = new HashSet<Weapon>();
    private SpaceObjectComponent powerGenerator;

    public SpaceShip(Texture texture, float startingX, float startingY, World world, int height, int witdh, boolean keepAspectRatio) {
        super(texture, startingX, startingY, world, height, witdh, keepAspectRatio);
    }


    /**
     * Mount new engine to the ship
     * @param newEngine
     * @return old engine if there was any
     */
    public Engine mountEngine(final Engine newEngine)
    {
        Engine oldEngine = engine;
        engine = newEngine;

        return oldEngine;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        shoot(delta);
    }

    private void shoot(float delta) {
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public float getAccelerationSpeed() {
        return engine.getThrust();
    }

    @Override
    public float getBreakingSpeed() {
        return engine.getReverseTrhust();
    }

    @Override
    public float getRotationSpeed() {
        return engine.getRotationThrust();
    }
}
