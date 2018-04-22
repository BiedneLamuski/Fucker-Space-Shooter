package com.biednelamuski.spacefuckershooter;

import com.badlogic.gdx.Game;
import com.biednelamuski.spacefuckershooter.screens.MainMenuScreen;

public class SpaceShooterGame extends Game{

    public static int WORLD_X = 1080;
    public static int WORLD_Y = 1920;


    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
