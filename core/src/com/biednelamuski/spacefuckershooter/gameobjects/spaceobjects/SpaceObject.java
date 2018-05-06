package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Align;

/**
 * Created by deamo on 24.03.2018.
 */

public class SpaceObject extends Actor{
    private final Body body;

    private MoveToAction currentMoveAction;
    private float ellapsedTime = 0;

    private Sprite image;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;

    public SpaceObject(Texture texture, float startingX, float startingY, World world) {
        setPosition(startingX, startingY);

        image = new Sprite(texture);
        setHeight(image.getHeight());
        setWidth((image.getWidth()));

        body = createPhysicalBody(world);
    }

    private Body createPhysicalBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX(), getY());

        return world.createBody(bodyDef);
    }

    public SpaceObject(String imagePath, float animationSpeed, float startingX, float startingY, World world) {
        setPosition(startingX, startingY);

        new TextureAtlas();

        textureAtlas = new TextureAtlas(Gdx.files.internal(imagePath));
        animation = new Animation<TextureRegion>(animationSpeed, textureAtlas.getRegions());

        body = createPhysicalBody(world);
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

        setPosition(body.getPosition().x, body.getPosition().y);
    }

    public void move(MoveToAction moveAction) {

        if(!getActions().contains(moveAction, true))
        {
            removeAction(currentMoveAction);
            addAction(moveAction);
        }
    }

    public Body getBody() {
        return body;
    }
}
