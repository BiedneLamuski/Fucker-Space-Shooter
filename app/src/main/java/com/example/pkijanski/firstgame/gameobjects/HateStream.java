package com.example.pkijanski.firstgame.gameobjects;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.ImageView;

/**
 * Created by PKijanski on 07.03.2018.
 */

public class HateStream {

    private Bitmap      hateStream;
    private int         x, y;
    private boolean     isMoving = false;
    private int         yVelocity = 20;

    private final static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;

    public HateStream (Bitmap bitmap) {
        hateStream = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        x = screenX;
        y = screenY;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(hateStream, x, y, null);
    }

    public void updateCords(final int x, final  int y) {
        this.x = x - (hateStream.getWidth()/2);
        this.y = y - hateStream.getHeight();
    }

    public void update() {
        if (isMoving) {
            y -= yVelocity;

            if (y<0) {
                isMoving = false;
                x = screenX;
                y = screenY;
            }
        }
    }

    public void shoot(Point point) {
        updateCords(point.x, point.y);
        isMoving = true;
    }
}
