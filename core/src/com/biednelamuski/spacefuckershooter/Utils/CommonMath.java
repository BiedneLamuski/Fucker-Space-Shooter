package com.biednelamuski.spacefuckershooter.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

/**
 * Class for storing common static methods
 */
public final class CommonMath {

    private CommonMath(){}

    public static double calculateDirection(double deltaX, double deltaY)
    {
        return Math.atan2(deltaY, deltaX); // Math.atan2(deltaY, deltaX) does the same thing but checks for deltaX being zero to prevent divide-by-zero exceptions
    }

    public static double calculateRotation(double deltaX, double deltaY) {
        return Math.atan2(-deltaX, deltaY) *180 / Math.PI;
    }

    public static double calculateDistance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }
}
