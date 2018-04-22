package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.moveactions.MoveAction;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToDirection;

/**
 * Created by deamo on 24.03.2018.
 */

public final class SpaceObject extends Actor implements Shooting{
    final static int SHOOTER_WIDTH = 150;

    //in %. How big it should be respectively to the screen.
    final static float size = 10f/100;

    private MoveToAction moveAction;

    private Sprite image;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;
    private float ellapsedTime = 0;

    public SpaceObject(String imagePath, float startingX, float startingY, MoveToAction moveAction) {

        this.moveAction = moveAction;
        setPosition(startingX, startingY);
        Texture texture = new Texture(imagePath);

        image = new Sprite(texture);
    }

    public SpaceObject(String imagePath, float animationSpeed, float startingX, float startingY, MoveToAction moveAction) {

        this.moveAction = moveAction;
        setPosition(startingX, startingY);

        new TextureAtlas();

        textureAtlas = new TextureAtlas(Gdx.files.internal(imagePath));
        animation = new Animation<TextureRegion>(animationSpeed, textureAtlas.getRegions());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if(animation != null) {
            ellapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion textureRegion = (TextureRegion) animation.getKeyFrame(ellapsedTime, true);
            batch.draw(textureRegion, getX(), getY());
        }
        else {
            image.setPosition(getX(), getY());
            image.setRotation(getRotation());
            image.draw(batch);
        }

    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void shoot(float x, float y) {
        System.out.println("pew pew");

        SpaceObject projectile = new SpaceObject("sci-fi-effects/pulsating_star/03.png", getX(Align.center), getY(Align.center), new MoveToDirection(800));
        projectile.moveTo(x,y);
        getStage().addActor(projectile);
    }

    public void moveTo(float x, float y) {
        moveAction.setPosition(x, y, Align.center);

        if(!getActions().contains(moveAction, true))
        {
            addAction(moveAction);
        }
    }
}
