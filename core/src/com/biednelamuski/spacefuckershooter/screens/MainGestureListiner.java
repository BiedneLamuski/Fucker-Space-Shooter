package com.biednelamuski.spacefuckershooter.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.controls.PlayerControls;
import com.biednelamuski.spacefuckershooter.gameobjects.SpaceObject;
import com.biednelamuski.spacefuckershooter.moveactions.MoveToFinger;

class MainGestureListiner extends ActorGestureListener implements PlayerControls {

    final private SpaceObject playerShip;
    private boolean screenTouched = false;

    public MainGestureListiner(SpaceObject playerShip) {
        this.playerShip = playerShip;
    }



    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        playerShip.shoot(x - playerShip.getX(Align.center), y - playerShip.getY(Align.center));
    }

    @Override
    public void fling(InputEvent event, float velocityX, float velocityY, int button) {
//            playerShip.shoot(velocityX, velocityY);
        System.out.println("Velocity: " + velocityX + " / " + velocityY);
            playerShip.moveTo(velocityX, velocityY);


//            double theta = 180 * Math.PI * Math.atan2(-(Math.cos(Math.atan2(velocityY, velocityX))), 1 - (Math.sin(Math.atan2(velocityY, velocityX))));

            double theta = Math.toDegrees(Math.atan2(-velocityX, velocityY));
            System.out.println("Old Rotation: " + playerShip.getRotation() + " Rotation: " + theta);
            playerShip.setRotation((float) theta);
//        playerShip.setRotation(playerShip.getRotation() - 90);
    }

}
