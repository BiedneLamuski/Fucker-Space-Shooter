package com.example.pkijanski.firstgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pkijanski.firstgame.gameobjects.Background;
import com.example.pkijanski.firstgame.gameobjects.Character;
import com.example.pkijanski.firstgame.gameobjects.HateStream;

/**
 * Created by PKijanski on 05.03.2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread  thread;
    private Character   character;
    private Background  background;
    private HateStream  hateStream;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        character = new Character(BitmapFactory.decodeResource(getResources(), R.drawable.fucker));
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.cosmos));
        hateStream = new HateStream(BitmapFactory.decodeResource(getResources(), R.drawable.hate));
        thread.isRunning(true);
        thread.start();;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.isRunning(false);
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        hateStream.update();
        character.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(null != canvas) {
            background.draw(canvas);
            character.draw(canvas);
            hateStream.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        int eventaction = event.getAction();

        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                character.stopStart(new Point(x, y));
                hateStream.shoot(character.getCurrentPosition());
                break;

            case MotionEvent.ACTION_MOVE:
                //not used now
                break;

            case MotionEvent.ACTION_UP:
                character.stopStart(new Point(x, y));
                break;
        }
        return true;
    }

}
