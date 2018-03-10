package com.example.pkijanski.firstgame.gameobjects;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by PKijanski on 07.03.2018.
 */

public class Background {

    private Bitmap background;

    private final static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
    private final static int orientation = Resources.getSystem().getConfiguration().orientation;
    private final static int portraitRot = 90;
    private final static int landscapeRot = 90;

    public Background(Bitmap bitmap) {
        Matrix mat = new Matrix();
        mat.postRotate(landscapeRot);

        if (Configuration.ORIENTATION_PORTRAIT == orientation) {
          //  mat.postRotate(portraitRot);
        }

        background = Bitmap.createScaledBitmap(bitmap,
                screenX, //bitmap.getWidth(),
                screenY, //bitmap.getHeight(),
                true);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
    }

}
