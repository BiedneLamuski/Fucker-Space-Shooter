package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.biednelamuski.spacefuckershooter.controls.PlayerControls;
import com.biednelamuski.spacefuckershooter.gameobjects.GameWorld;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceShip;
import com.biednelamuski.spacefuckershooter.moveactions.MoveAction;
import com.biednelamuski.spacefuckershooter.moveactions.MoveInDirection;

public class MainGestureListiner extends ActorGestureListener implements PlayerControls {

    private static final float ZOOM_THRESHOLD = 0.0f;
    private final SpaceShip playerShip;
    private final GameWorld gameWorld;
    private float initialDistance;
    private float initialZoom;

    public MainGestureListiner(SpaceShip playerShip, GameWorld gameWorld)
    {
        this.playerShip = playerShip;
        this.gameWorld = gameWorld;
    }


    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        super.tap(event, x, y, count, button);
        MoveAction moveAction = new MoveInDirection(x, y);
        playerShip.move(moveAction);
    }

    @Override
    public void fling(InputEvent event, float velocityX, float velocityY, int button) {
    }


    @Override
    public void zoom(InputEvent event, float initialDistance, float distance) {
        super.zoom(event, initialDistance, distance);

        OrthographicCamera camera = gameWorld.getCamera();
        if(this.initialDistance != initialDistance)
        {
            this.initialDistance = initialDistance;
            this.initialZoom = camera.zoom;
        }

        float newZoom = initialZoom * initialDistance/distance;

        if(Math.abs(camera.zoom - newZoom) < ZOOM_THRESHOLD)
        {
            return;
        }

        System.out.println("New zoom:" + initialZoom * initialDistance/distance);
        camera.zoom = initialZoom * initialDistance/distance;
    }

    @Override
    public void pinch(InputEvent event, Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        super.pinch(event, initialPointer1, initialPointer2, pointer1, pointer2);
    }
}
