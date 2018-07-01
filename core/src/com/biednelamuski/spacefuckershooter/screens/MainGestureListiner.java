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

    final private SpaceShip playerShip;
    private GameWorld gameWorld;


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
        OrthographicCamera camera = ((OrthographicCamera)gameWorld.getCamera());
        camera.zoom *= initialDistance/distance;
//        camera.update();
        System.out.println("ZOOOMING!: " + initialDistance + " -> " + distance);
    }

    @Override
    public void pinch(InputEvent event, Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        super.pinch(event, initialPointer1, initialPointer2, pointer1, pointer2);
    }
}
