package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.SpaceShooterGame;
import com.biednelamuski.spacefuckershooter.gameobjects.Background;
import com.biednelamuski.spacefuckershooter.gameobjects.GameWorld;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;

import java.util.HashSet;
import java.util.Set;


public class GameScreen implements Screen {
    /**************************************************************************************************
     VARIABLES
     **************************************************************************************************/

    private Set<SpaceObject> spaceObjects = new HashSet<SpaceObject>();
    private GameWorld gameworld;
    private SpaceObject playerShip;
    private Background image;
    private final AssetManager assetManager = new AssetManager();

    public GameScreen() {




        assetManager.getLogger().setLevel(Logger.DEBUG);
        assetManager.load("backgrounds/background_green.jpg", Texture.class);
        assetManager.load("spaceships/battleship_1.png", Texture.class);
        assetManager.finishLoading();
    }


    @Override
    public void show() {

        gameworld = new GameWorld(new FitViewport(SpaceShooterGame.WORLD_X, SpaceShooterGame.WORLD_Y), assetManager);


        Gdx.input.setInputProcessor(gameworld);

        image = new Background((Texture) assetManager.get("backgrounds/background_green.jpg"));
        gameworld.addActor(image);

        playerShip = gameworld.createPlayerShip();

    }

    @Override
    public void render(float delta) {

        gameworld.act(delta);
        gameworld.getCamera().position.set(playerShip.getX(Align.center), playerShip.getY(Align.center),0);
        image.setSize(gameworld.getCamera().viewportWidth*gameworld.getCamera().zoom, gameworld.getCamera().viewportHeight*gameworld.getCamera().zoom);
        image.setPosition(gameworld.getCamera().position.x, gameworld.getCamera().position.y, Align.center);
        clearScreen();
        gameworld.draw();
    }


    @Override
    public void resize(int width, int height) {
        gameworld.getViewport().update(width, height);
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
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
