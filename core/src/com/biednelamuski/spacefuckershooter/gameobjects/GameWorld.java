package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.SpaceShooterGame;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.PlayerShipFactory;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceShip;
import com.biednelamuski.spacefuckershooter.screens.MainGestureListiner;

import box2dLight.PointLight;
import box2dLight.RayHandler;

public class GameWorld extends Stage{

    private final AssetManager assetManager;
    private final World world;
    private final OrthographicCamera camera;
    private final RayHandler rayHandler;

    // scaleFactor pixesl = 1 m
    public static final float scaleFactor = 1000;
    public static final float ZOOMING_SPEED = 3f;
    private float cameraZoom;


    public GameWorld(final FitViewport fitViewport, final AssetManager assetManager)
    {
        super(fitViewport);
        camera = new OrthographicCamera();
        fitViewport.setCamera(camera);
        this.assetManager = assetManager;
        world = new World(new Vector2(0,0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(false);

        new PointLight(rayHandler, 100, new Color(1,1,1,50), 10000, SpaceShooterGame.WORLD_X, SpaceShooterGame.WORLD_Y);

    }

    @Override
    public void act(float delta) {
        world.step(delta, 1, 1);
        super.act(delta);
    }
    public World getPhysicalWorld()
    {
        return world;
    }

    @Override
    public void draw() {
        super.draw();
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    public SpaceObject createPlayerShip()
    {
        SpaceShip playerShip = PlayerShipFactory.createPlayerShip(assetManager, world);
        addActor(playerShip);
        addListener(new MainGestureListiner(playerShip, this));
        return playerShip;
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }
}
