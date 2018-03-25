package com.biednelamuski.spacefuckershooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.biednelamuski.spacefuckershooter.gameobjects.Background;
import com.biednelamuski.spacefuckershooter.gameobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToFinger;
import com.biednelamuski.spacefuckershooter.moveactions.MoveUp;
import com.biednelamuski.spacefuckershooter.moveactions.Moveable;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameView implements ApplicationListener, InputProcessor {

    public static int SCREEN_X;
    public static int SCREEN_Y;

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
    private SpaceObject character;
    private Background background;

    private OrthographicCamera camera;


    @Override
    public void create() {
        SCREEN_X = Gdx.graphics.getWidth();
        SCREEN_Y = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(SCREEN_X, SCREEN_Y);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        // pick a viewport that suits your thing, ExtendViewport is a good start

        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();

        background = new Background("cosmos.jpg");
        createPlayerShip();
    }

    @Override
    public void resize(int width, int height) {
        SCREEN_X = width;
        SCREEN_Y = height;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        background.draw(batch);

//        Kurwa gdyby android obsugiwal jave 8...
//        spaceObjects.forEach(so -> so.draw(batch));
        for(SpaceObject spaceObject : spaceObjects) {
            spaceObject.draw(batch);
        }
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        background.dispose();
        for(SpaceObject spaceObject : spaceObjects) {
            spaceObject.dispose();
        }
        batch.dispose();
    }




    private void createPlayerShip() {

        Moveable playerMove = new MoveToFinger(PLAYER_VELOCITY);
        character = new SpaceObject("spaceships/battleship_1.png", (SCREEN_X / 2), 120, playerMove);

        spaceObjects.add(character);
    }

    public void update() {

        shoot();

        Iterator<SpaceObject> spacjeObjectsIterator = spaceObjects.iterator();

        while (spacjeObjectsIterator.hasNext()) {
            SpaceObject object = spacjeObjectsIterator.next();
            object.update();
            if (object.getCurrentPosition().x < 0 || object.getCurrentPosition().y < 0) {
                System.out.println("Deleting object: " + object.toString());
                spacjeObjectsIterator.remove();
            }
        }

    }

    private void shoot() {
        long currentTimeMillis = System.currentTimeMillis();
        if (screenTouched && currentTimeMillis - lastTimeWhenshooted > SHOOTING_SPPED) {
            System.out.println("Shooting!");
            Moveable moveUp = new MoveUp(10);
            spaceObjects.add(new SpaceObject("sci-fi-effects/pulsating_beam/Beam.atlas", 1/15f, character.getCurrentPosition().x, character.getCurrentPosition().y, moveUp));
            lastTimeWhenshooted = currentTimeMillis;
        }


    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenTouched = true;
        Vector3 vector = new Vector3(screenX, screenY,0);
        camera.unproject(vector);
        character.moveTo(new GridPoint2((int)vector.x, (int)vector.y));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenTouched = false;
        character.stopMoving();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector3 vector = new Vector3(screenX, screenY,0);
        camera.unproject(vector);
        character.moveTo(new GridPoint2((int)vector.x, (int)vector.y));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
