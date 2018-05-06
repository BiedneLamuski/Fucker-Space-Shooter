package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.controls.PlayerControls;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceShip;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToDirection;

public class MainGestureListiner extends ActorGestureListener implements PlayerControls {

    final private SpaceShip playerShip;

    public MainGestureListiner(SpaceShip playerShip)
    {
        this.playerShip = playerShip;
    }



    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {


        playerShip.moveTo(x, y);
    }

    @Override
    public void fling(InputEvent event, float velocityX, float velocityY, int button) {
    }

}
