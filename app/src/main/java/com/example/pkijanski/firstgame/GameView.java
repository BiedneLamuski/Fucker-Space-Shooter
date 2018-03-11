package com.example.pkijanski.firstgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pkijanski.firstgame.gameobjects.Background;
import com.example.pkijanski.firstgame.gameobjects.MoveUp;
import com.example.pkijanski.firstgame.gameobjects.SpaceObject;
import com.example.pkijanski.firstgame.gameobjects.moveactions.MoveToFinger;
import com.example.pkijanski.firstgame.gameobjects.moveactions.Moveable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by PKijanski on 05.03.2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private final static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
    private static final int PLAYER_VELOCITY = 30;


    private MainThread  thread;
    private SpaceObject character;
    private Background  background;
    private SpaceObject  hateStream;

    private long lastTimeWhenshooted = 0;

    private Set<SpaceObject> spaceObjects = new HashSet<>();
    private boolean screenTouched = false;
    private long shootingSpped = 500;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        createPlayerShip();
        background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.cosmos));
        thread.isRunning(true);
        thread.start();
    }

    private void createPlayerShip() {

        Moveable playerMove = new MoveToFinger(PLAYER_VELOCITY);
        character = new SpaceObject(BitmapFactory.decodeResource(getResources(), R.drawable.spaceship), (screenX / 2), screenY - 20, playerMove);

        spaceObjects.add(character);
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

        shoot();

        Iterator<SpaceObject> spacjeObjectsIterator = spaceObjects.iterator();

        while(spacjeObjectsIterator.hasNext()) {
            SpaceObject object = spacjeObjectsIterator.next();
            object.update();
            if(object.getCurrentPosition().x < 0 || object.getCurrentPosition().y < 0) {
                System.out.println("Deleting object: "  + object.toString());
                spacjeObjectsIterator.remove();
            }
        }

    }

    private void shoot() {
        long currentTimeMillis = System.currentTimeMillis();
        if(screenTouched && currentTimeMillis - lastTimeWhenshooted > shootingSpped) {
            System.out.println("Shooting!");
            Moveable moveUp = new MoveUp(20);
            spaceObjects.add(new SpaceObject(BitmapFactory.decodeResource(getResources(), R.drawable.hate), character.getCurrentPosition().x, character.getCurrentPosition().y, moveUp));
            lastTimeWhenshooted = currentTimeMillis;
        }


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(null != canvas) {
            background.draw(canvas);

            for(SpaceObject object : spaceObjects) {
                object.draw(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();
        int eventaction = event.getAction();


        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                screenTouched = true;

                character.moveTo(new Point(x, y));

                break;

            case MotionEvent.ACTION_MOVE:
                character.moveTo(new Point(x, y));
                break;

            case MotionEvent.ACTION_UP:
                screenTouched = false;
                character.stopMoving();
                break;
        }
        return true;
    }

}
