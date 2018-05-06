package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.PlayerShipFactory;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.screens.MainGestureListiner;

public class GameWorld extends Stage{

    private final AssetManager assetManager;
    private final World world;

    private final SpaceObject playerShip;

    public GameWorld(final FitViewport fitViewport, final AssetManager assetManager)
    {
        super(fitViewport);
        this.assetManager = assetManager;
        world = new World(new Vector2(0,0), true);
        playerShip = createPlayerShip();
    }

    @Override
    public void act(float delta) {
        world.step(delta, 1, 1);
        super.act(delta);
    }


    private SpaceObject createPlayerShip()
    {
        SpaceObject playerShip = PlayerShipFactory.createPlayerShip(assetManager, world);
        addActor(playerShip);
        addListener(new MainGestureListiner(playerShip));
        return playerShip;
    }


}
