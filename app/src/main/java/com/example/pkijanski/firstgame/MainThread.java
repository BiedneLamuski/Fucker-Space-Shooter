package com.example.pkijanski.firstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by PKijanski on 05.03.2018.
 */

public class MainThread extends Thread {

    private SurfaceHolder   surfaceHolder;
    private GameView        gameView;
    private boolean         running;
    public static Canvas    canvas;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    public void isRunning(boolean isRunning) {
        this.running = isRunning;
    }

    @Override
    public void run() {
        while(running){
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if(null != canvas) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
