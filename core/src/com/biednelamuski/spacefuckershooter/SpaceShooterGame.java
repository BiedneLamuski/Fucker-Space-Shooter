package com.biednelamuski.spacefuckershooter;

import com.badlogic.gdx.Game;
import com.biednelamuski.spacefuckershooter.screens.MainMenuScreen;

public class SpaceShooterGame extends Game{

    public static int WORLD_X = 54;
    public static int WORLD_Y = 96;


    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
