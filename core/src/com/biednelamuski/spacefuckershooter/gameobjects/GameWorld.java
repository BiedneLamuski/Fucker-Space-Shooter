package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.PlayerShipFactory;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceShip;
import com.biednelamuski.spacefuckershooter.screens.MainGestureListiner;

public class GameWorld extends Stage{

    private final AssetManager assetManager;
    private final World world;

    // scaleFactor pixesl = 1 m
    public static final float scaleFactor = 1000;


    public GameWorld(final FitViewport fitViewport, final AssetManager assetManager)
    {
        super(fitViewport);
        this.assetManager = assetManager;
        world = new World(new Vector2(0,0), true);
        this.setDebugAll(true);
    }

    @Override
    public void act(float delta) {
        world.step(delta, 1, 1);
        super.act(delta);
    }


    public SpaceObject createPlayerShip()
    {
        SpaceShip playerShip = PlayerShipFactory.createPlayerShip(assetManager, world);
        addActor(playerShip);
        addListener(new MainGestureListiner(playerShip));
        return playerShip;
    }
}
