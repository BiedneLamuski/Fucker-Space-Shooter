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
        return (Math.atan2(deltaY, deltaX) - Math.atan2(1, 0)) *180 / Math.PI;
    }
}
