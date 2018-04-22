package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.biednelamuski.spacefuckershooter.SpaceShooterGame;

public class MainMenuScreen implements Screen {

    private Stage stage;

    private SpaceShooterGame game;

    private Texture menuBacground;
    private Texture startButtonTexture;


    public MainMenuScreen(final SpaceShooterGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(SpaceShooterGame.WORLD_X, SpaceShooterGame.WORLD_Y));
        Gdx.input.setInputProcessor(stage);

        menuBacground = new Texture("backgrounds/cosmos.jpg");
        Image image = new Image(menuBacground);
        stage.addActor(image);

        startButtonTexture = new Texture("startButton.png");
        ImageButton startButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(startButtonTexture)),new TextureRegionDrawable(new TextureRegion(startButtonTexture)));
        startButton.setPosition(SpaceShooterGame.WORLD_X/2f, SpaceShooterGame.WORLD_Y/2f, Align.center);
        startButton.addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                game.setScreen(new GameScreen());
                dispose();
            }
        });


        stage.addActor(startButton);
    }

    @Override
    public void render(final float delta) {

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
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
        stage.dispose();
        menuBacground.dispose();
        startButtonTexture.dispose();
    }
}
