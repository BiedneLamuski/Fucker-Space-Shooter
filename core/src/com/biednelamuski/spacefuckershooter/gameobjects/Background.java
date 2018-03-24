package com.biednelamuski.spacefuckershooter.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.biednelamuski.spacefuckershooter.GameView;


public class Background implements Drawable{

    private Sprite background;

    //TODO: to implement orientation support in libGDX
//    private final static int orientation = Resources.getSystem().getConfiguration().orientation;
    private final static int portraitRot = 90;
    private final static int landscapeRot = 90;

    public Background(String imagePath) {
            Texture tex = new Texture(imagePath);
            background = new Sprite(tex);
            background.setSize(GameView.SCREEN_X, GameView.SCREEN_Y);
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
    }

    @Override
    public void draw(SpriteBatch batch) {
        background.draw(batch);
    }
}
