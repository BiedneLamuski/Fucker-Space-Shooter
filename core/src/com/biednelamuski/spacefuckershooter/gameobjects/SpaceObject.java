package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.biednelamuski.spacefuckershooter.GameView;
import com.biednelamuski.spacefuckershooter.moveactions.Moveable;

/**
 * Created by deamo on 24.03.2018.
 */

public class SpaceObject implements Moveable, Drawable{
    final static int SHOOTER_WIDTH = 150;

    final static float ratio = 25f/100;


    private Sprite image;
    private Moveable moveType;

    public SpaceObject(String imagePath, int startingX, int startingY, Moveable moveType) {

        Texture texture = new Texture(imagePath);
//        int ratio = SHOOTER_WIDTH /GameView.SCREEN_X * 100;
        image = new Sprite(texture);
        image.setSize(GameView.SCREEN_X * ratio, GameView.SCREEN_Y * ratio);

        this.moveType = moveType;
        this.moveType.setPosition(new GridPoint2(startingX, startingY ));
    }

    public void draw(SpriteBatch batch) {

        GridPoint2 currentPosition = moveType.getCurrentPosition();
        currentPosition.add((int)(-1*image.getWidth()/2), (int)(-1*image.getHeight()/2));
        image.setPosition(currentPosition.x, currentPosition.y);
        image.draw(batch);
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
        image.getTexture().dispose();
    }

}
