package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.biednelamuski.spacefuckershooter.SpaceShooterGame;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.components.Engine;

public abstract class PlayerShipFactory {

    public static SpaceShip createPlayerShip(final AssetManager assetManager, World world)
    {

        SpaceShip playerShip = new SpaceShip((Texture) assetManager.get("spaceships/battleship_1.png"), (SpaceShooterGame.WORLD_X / 2), 120, world);

        createBasicEngine(playerShip);


        playerShip.getBody().setLinearDamping(0);

        // Now define the dimensions of the physics shape
        PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions
//        as our sprite
        shape.setAsBox(playerShip.getWidth(), playerShip.getHeight());

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the
//        body
        // you also define it's properties like density, restitution and others
//        we will see shortly
        // If you are wondering, density and area are used to calculate over all
//        mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.01f;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0;

        Fixture fixture = playerShip.getBody().createFixture(fixtureDef);

        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();

        return playerShip;
    }

    private static void createBasicEngine(SpaceShip playerShip)
    {
        float energyConsumption = 100;
        float thrust = 1000;
        float reverseThrust = 1000;
        float rotationTrhust = 300000;
        Engine basicEngine = new Engine(energyConsumption, thrust, reverseThrust, rotationTrhust);
        playerShip.mountEngine(basicEngine);
    }
}
