package com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

public class SpaceObject extends Actor implements Moveable {
    private final Body body;

    private MoveToAction currentMoveAction;
    private float ellapsedTime = 0;

    private Sprite image;
    private Animation<TextureRegion> animation;
    private TextureAtlas textureAtlas;
    private ShapeRenderer renderer = new ShapeRenderer();

    public SpaceObject(Texture texture, float startingX, float startingY, World world, int height, float width, boolean keepAspectRatio) {
        image = new Sprite(texture);

        if(keepAspectRatio)
        {
            float aspectRatio = image.getHeight()/image.getWidth();
            image.setSize(height/aspectRatio, height);
        }
        else {
            image.setSize(image.getHeight()*height, image.getWidth() * width);
        }

        image.setOrigin(image.getWidth()/2f, image.getHeight()/2f);

        setHeight(image.getHeight());
        setWidth((image.getWidth()));
        setOrigin(getWidth()/2f, getHeight()/2f);

        body = createPhysicalBody(world);
        setPosition(startingX, startingY);
    }

    private Body createPhysicalBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(getX(), getY());
        return world.createBody(bodyDef);
    }

    public SpaceObject(String imagePath, float animationSpeed, float startingX, float startingY, World world, float size) {
        setPosition(startingX, startingY);

        new TextureAtlas();

        textureAtlas = new TextureAtlas(Gdx.files.internal(imagePath));
        animation = new Animation<TextureRegion>(animationSpeed, textureAtlas.getRegions());

        body = createPhysicalBody(world);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
            image.setRotation(this.getRotation());
            image.setPosition(getX(), getY());
            image.draw(batch);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setLineHeight(5f);
        font.draw(batch, "Pos X: " + body.getPosition().x + "\nPos y:" + body.getPosition().y + "\n\nSpeed x: " + body.getLinearVelocity().x + "\nSpeed y:" + body.getLinearVelocity().y , getX(), getY()-5);

    }

    @Override
    public void act(float delta) {
        super.act(delta);



        setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        setX(body.getPosition().x, Align.center);
        setY(body.getPosition().y, Align.center);
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
        body.setTransform(this.getX(alignment), this.getY(alignment), body.getAngle());
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        body.setTransform(this.getX(), this.getY(), body.getAngle());
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
        if (!getActions().contains(moveAction, true)) {
            removeAction(currentMoveAction);
            currentMoveAction = moveAction;
            addAction(moveAction);
        }
    }
}
