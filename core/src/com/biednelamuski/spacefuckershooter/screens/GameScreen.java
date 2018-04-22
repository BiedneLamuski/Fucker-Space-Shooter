package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.SpaceShooterGame;
import com.biednelamuski.spacefuckershooter.gameobjects.Background;
import com.biednelamuski.spacefuckershooter.gameobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToDirection;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToFinger;

import java.util.HashSet;
import java.util.Set;


public class GameScreen implements Screen {
    /**************************************************************************************************
     STATIC CONFIGURABLE
     **************************************************************************************************/
    private final static long SHOOTING_SPPED = 500;
    private final static int PLAYER_VELOCITY = 5;

    /**************************************************************************************************
     VARIABLES
     **************************************************************************************************/


    private SpriteBatch batch;

    private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
    private boolean screenTouched = false;
    private long lastTimeWhenshooted = 0;
    private SpaceObject playerShip;
    private Stage stage;
    private final AssetManager assetManager = new AssetManager();

    public GameScreen() {
        assetManager.getLogger().setLevel(Logger.DEBUG);
        assetManager.load("backgrounds/background_green.jpg", Texture.class);
        assetManager.finishLoading();
    }


    @Override
    public void show() {
        stage = new Stage(new FitViewport(SpaceShooterGame.WORLD_X, SpaceShooterGame.WORLD_Y));

        Gdx.input.setInputProcessor(stage);


        batch = new SpriteBatch();

        Background image = new Background((Texture) assetManager.get("backgrounds/background_green.jpg"));
        image.setPosition(SpaceShooterGame.WORLD_X/2, SpaceShooterGame.WORLD_Y/2, Align.center);
        stage.addActor(image);

        createPlayerShip();
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.getCamera().position.set(playerShip.getX(Align.center), playerShip.getY(Align.center),0);
        shoot();
        clearScreen();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void createPlayerShip() {

        playerShip = new SpaceObject("spaceships/battleship_1.png", (SpaceShooterGame.WORLD_X / 2), 120, new MoveToDirection(300));

        playerShip.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                playerShip.moveTo(playerShip.getX(Align.center), playerShip.getY(Align.center));
            }
        });

        stage.addActor(playerShip);
        stage.addListener(new MainGestureListiner(playerShip));

    }

    private void shoot() {
        long currentTimeMillis = System.currentTimeMillis();
        if (screenTouched && currentTimeMillis - lastTimeWhenshooted > SHOOTING_SPPED) {
            System.out.println("Shooting!");

            lastTimeWhenshooted = currentTimeMillis;
        }
    }
}
