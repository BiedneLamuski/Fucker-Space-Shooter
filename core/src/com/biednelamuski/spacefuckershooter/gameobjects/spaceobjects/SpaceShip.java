package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
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

    public SpaceShip(Texture texture, float startingX, float startingY, World world) {
        super(texture, startingX, startingY, world);
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

    public void moveTo(float x, float y) {




        engine.getMoveToAction().setPosition(x, y, Align.center);
        move(engine.getMoveToAction());
    }
}
