package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.controls.PlayerControls;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceShip;
import com.biednelamuski.spacefuckershooter.moveactions.MoveAction;
import com.biednelamuski.spacefuckershooter.moveactions.MoveInDirection;

public class MainGestureListiner extends ActorGestureListener implements PlayerControls {

    final private SpaceShip playerShip;



    public MainGestureListiner(SpaceShip playerShip)
    {
        this.playerShip = playerShip;
    }



    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {

        MoveAction moveAction = new MoveInDirection(x-playerShip.getX(Align.center), y - playerShip.getY(Align.center));
        playerShip.move(moveAction);
    }

    @Override
    public void fling(InputEvent event, float velocityX, float velocityY, int button) {
    }

}
