package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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


    private Box2DDebugRenderer debugRenderer;


    public GameWorld(final FitViewport fitViewport, final AssetManager assetManager)
    {
        super(fitViewport);
        camera = new OrthographicCamera();
//        camera.zoom *= 1;
        fitViewport.setCamera(camera);
        this.assetManager = assetManager;
        this.setDebugAll(true);
        world = new World(new Vector2(0,0), false);
        rayHandler = initRayRenderer();
        debugRenderer = new Box2DDebugRenderer();

//        PointLight pointLight = new PointLight(rayHandler, 10000, Color.WHITE, 5000/UNIT_TO_PIXEL, SpaceShooterGame.WORLD_X/UNIT_TO_PIXEL, SpaceShooterGame.WORLD_Y/UNIT_TO_PIXEL);
        PointLight pointLight = new PointLight(rayHandler, 10000, Color.WHITE, 400, SpaceShooterGame.WORLD_X, SpaceShooterGame.WORLD_Y);
    }

//    private OrthographicCamera initBox2dCamera() {
//        OrthographicCamera b2dCam = new OrthographicCamera(camera.viewportWidth, camera.viewportHeight);
//        b2dCam.setToOrtho(false);
//        b2dCam.update();
//        return b2dCam;
//    }

    private RayHandler initRayRenderer()
    {
        RayHandler rayHandler = new RayHandler(world);
        rayHandler.setBlurNum(3);
        rayHandler.setShadows(false);
        return rayHandler;
    }
    @Override
    public void act(float delta) {
        world.step(delta, 6, 2);
        super.act(delta);
    }

    public World getPhysicalWorld()
    {
        return world;
    }

    @Override
    public void draw() {
        super.draw();

//        b2dCam.zoom = camera.zoom;
//        b2dCam.position.x = camera.position.x / UNIT_TO_PIXEL;
//        b2dCam.position.y = camera.position.y / UNIT_TO_PIXEL;


//        rayHandler.setCombinedMatrix(camera.combined.scale(UNIT_TO_PIXEL,UNIT_TO_PIXEL,UNIT_TO_PIXEL), camera.position.x/UNIT_TO_PIXEL, camera.position.y/UNIT_TO_PIXEL, camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
        debugRenderer.render(world, camera.combined);
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
