package com.example.pkijanski.firstgame.gameobjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by PKijanski on 06.03.2018.
 */

public class Character {
    final static int shooterWidth = 150;

    private Bitmap      character;

    private boolean     isMoving = true;
    private int         startingX, startingY;
    private int         x, y;
    private int         destX, destY;

    private final static int velocity = 10;

    private final static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;


    public Character (Bitmap bitmap) {
        int ratio = bitmap.getWidth() / shooterWidth;

        character = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / ratio, bitmap.getHeight() / ratio, true);
        x = (screenX / 2) - (character.getWidth() / 2);
        y = screenY - 20 - character.getHeight();

        startingX = x;
        startingY = y;
        destX = x;
        destY = y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(character, x, y, null);
    }

    public void updateCords(final int x, final  int y) {
        this.x = x;
        this.y = y;
    }

    public void update(){
        if (x != destX) {
            x += velocity * Math.signum(destX - x);

            if (destX < x + velocity && destX > x - velocity) {
                destX= x;
            }
        }
        if (y != destY) {
            y += velocity * Math.signum(destY -y);

            if (destY < y + velocity && destY > y - velocity) {
                destY = y;
            }
        }
    }

    public void stopStart(Point point) {
        if (isMoving) {
            isMoving = false;

            if (null != point) {
                destX = point.x - (character.getWidth()/2);
                destY = point.y - (character.getHeight()/2);
            }
        } else if (!isMoving) {
            isMoving = true;
        }
    }

    public Point getCurrentPosition() {
        Point point = new Point();
        point.set(this.x, this.y);
        return point;
    }

// Legacy code
//    public void stopStart() {
//        if (isMoving) {
//            xVelocity = 0;
//            yVelocity = 0;
//            isMoving = false;
//        } else if (!isMoving) {
//            xVelocity = lastXVelocity;
//            yVelocity = lastYVelocity;
//            isMoving = true;
//        }
//    }

//    public void autoMove() {
//        if (x<0 && y<0) {
//            x = startingX;
//            y = startingY;
//        } else {
//            x += xVelocity;
//            y += yVelocity;
//
//            if (x > screenX - character.getWidth() || x < 0) {
//                xVelocity *= -1;
//                lastXVelocity = xVelocity;
//            }
//            if (y > screenY - character.getHeight() || y < 0) {
//                yVelocity *= -1;
//                lastYVelocity = yVelocity;
//            }
//        }
//    }
}
