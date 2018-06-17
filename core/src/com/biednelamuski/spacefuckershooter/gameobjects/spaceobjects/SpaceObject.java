package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.gameobjects.GameWorld;
import com.biednelamuski.spacefuckershooter.moveactions.MoveAction;

/**
 * Created by deamo on 24.03.2018.
 */

public class SpaceObject extends Actor implements Moveable{
    private final Body body;

    private MoveToAction currentMoveAction;
    private float ellapsedTime = 0;

    private Sprite image;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;

    public SpaceObject(Texture texture, float startingX, float startingY, World world) {
        image = new Sprite(texture);
        setHeight(image.getHeight());
        setWidth((image.getWidth()));

        body = createPhysicalBody(world);
        setPosition(startingX, startingY);
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


        setX(body.getPosition().x* GameWorld.scaleFactor, Align.center);
        setY(body.getPosition().y * GameWorld.scaleFactor, Align.center);
        setRotation(body.getAngle() * MathUtils.radiansToDegrees);
//        System.out.println("Rot speed: " + (float)(getRotation() - rotation));
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        body.getPosition().set(this.getX()/GameWorld.scaleFactor, this.getY()/GameWorld.scaleFactor);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        body.getPosition().set(this.getX()/GameWorld.scaleFactor, this.getY()/GameWorld.scaleFactor);
    }

    @Override
    public float getAccelerationSpeed() {
        return 0;
    }

    @Override
    public float getBreakingSpeed() {
        return 0;
    }

    @Override
    public float getRotationSpeed() {
        return 0;
    }

    @Override
    public void move(MoveAction moveAction) {
        if(!getActions().contains(moveAction, true))
        {
            removeAction(currentMoveAction);
            currentMoveAction = moveAction;
            addAction(moveAction);
        }
    }
}
