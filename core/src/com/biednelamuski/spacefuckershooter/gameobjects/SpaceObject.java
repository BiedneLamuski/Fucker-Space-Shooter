package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import com.biednelamuski.spacefuckershooter.GameView;
import com.biednelamuski.spacefuckershooter.moveactions.Moveable;

/**
 * Created by deamo on 24.03.2018.
 */

public class SpaceObject implements Moveable, Drawable, Shooting{
    final static int SHOOTER_WIDTH = 150;

    //in %. How big it should be respectively to the screen.
    final static float size = 10f/100;


    private Sprite image;
    private Animation<TextureRegion> animation;
    private Moveable moveType;
    private TextureAtlas textureAtlas;
    private float ellapsedTime = 0;

    public SpaceObject(String imagePath, int startingX, int startingY, Moveable moveType) {

        Texture texture = new Texture(imagePath);

        float ratio = (float)texture.getWidth() / (float)texture.getHeight();
        image = new Sprite(texture);
        image.setSize(GameView.SCREEN_X * size, GameView.SCREEN_X * size / ratio);

        this.moveType = moveType;
        this.moveType.setPosition(new GridPoint2(startingX, startingY ));
    }

    public SpaceObject(String imagePath, float animationSpeed, int startingX, int startingY, Moveable moveType) {

        textureAtlas = new TextureAtlas(Gdx.files.internal(imagePath));
        animation = new Animation<TextureRegion>(animationSpeed, textureAtlas.getRegions());
        this.moveType = moveType;
        this.moveType.setPosition(new GridPoint2(startingX, startingY ));
    }

    public void draw(SpriteBatch batch) {
        GridPoint2 currentPosition = moveType.getCurrentPosition();

        if(animation != null) {
            ellapsedTime += Gdx.graphics.getDeltaTime();
            TextureRegion textureRegion = (TextureRegion) animation.getKeyFrame(ellapsedTime, true);
            currentPosition.add((int)(-1*textureRegion.getRegionWidth()/2), (int)(-1*textureRegion.getRegionHeight()/2));
            batch.draw(textureRegion, currentPosition.x, currentPosition.y);
        }
        else {
            currentPosition.add((int)(-1*image.getWidth()/2), (int)(-1*image.getHeight()/2));
            image.setPosition(currentPosition.x, currentPosition.y);
            image.draw(batch);
        }

    }

    public void update() {
        moveType.update();
    }

    @Override
    public void moveTo(GridPoint2 point) {
        moveType.moveTo(point);
    }

    @Override
    public void setPosition(GridPoint2 point) {

    }

    public void stopMoving() {
        moveType.stopMoving();
    }

    public GridPoint2 getCurrentPosition() {
        return moveType.getCurrentPosition();
    }

    @Override
    public void dispose() {
        if(image != null)  image.getTexture().dispose();
        if(textureAtlas != null) textureAtlas.dispose();
    }

    @Override
    public void shoot() {

    }
}
