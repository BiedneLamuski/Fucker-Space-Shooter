package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.biednelamuski.spacefuckershooter.Utils.CommonMath;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveInDirection extends MoveAction {

    private double direction;
    private double endAngle;
    private double startingAngle;
    private boolean isRotated;

    private RotationDirection rotationDirection;

    private enum RotationDirection {
        LEFT,
        RIGHT;
    }


    public MoveInDirection(double x, double y) {
        direction = CommonMath.calculateDirection(x, y);
        endAngle = CommonMath.calculateRotation(x, y);
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        startingAngle = moveableTarget.getBody().getAngle();
        determineRotationRirection();

    }

    private void determineRotationRirection() {
        //coordinates are inverted so end result is also inverted;)
        rotationDirection = ((endAngle - startingAngle <= 180) && (endAngle - startingAngle >= 0)) ? RotationDirection.LEFT : RotationDirection.RIGHT;
    }

    @Override
    public boolean act(float delta) {
        return (rotateTowards(delta) && moveTowards(delta));
    }

    private boolean rotateTowards(float delta) {

        double currentRotation = moveableTarget.getBody().getAngle() * MathUtils.radiansToDegrees;
        float xPos = moveableTarget.getBody().getWorldCenter().x;
        float yPos = moveableTarget.getBody().getWorldCenter().y;

        if(isRotated)
        {
            return true;
        }

        System.out.println("Velocity: " + moveableTarget.getBody().getAngularVelocity() + "\t" + currentRotation + "/" + endAngle);
        if(rotationDirection == RotationDirection.RIGHT)
        {
            if(currentRotation + moveableTarget.getBody().getAngularVelocity() > (endAngle - startingAngle)/2)
            {
                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos + moveableTarget.getHeight() / 2, true);
                moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos - moveableTarget.getHeight() / 2, true);
            } else if(currentRotation <= (endAngle - startingAngle)/2)   {
                if (currentRotation + moveableTarget.getBody().getAngularVelocity() > endAngle) {
                    moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos + moveableTarget.getHeight() / 2, true);
                    moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos - moveableTarget.getHeight() / 2, true);
                }
                else {
                    isRotated = true;
                    moveableTarget.getBody().setAngularVelocity(0);
                }
            }

        } else {

            if(currentRotation + moveableTarget.getBody().getAngularVelocity() < (endAngle - startingAngle)/2)
            {
                moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos + moveableTarget.getHeight() / 2, true);
                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos - moveableTarget.getHeight() / 2, true);
            } else if(currentRotation >= (endAngle - startingAngle)/2)   {
                if (currentRotation + moveableTarget.getBody().getAngularVelocity() < endAngle) {
                    moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos + moveableTarget.getHeight() / 2, true);
                    moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, xPos, yPos - moveableTarget.getHeight() / 2, true);
                }
                else {
                    isRotated = true;
                    moveableTarget.getBody().setAngularVelocity(0);
                }
            }        }

//        double currentRotation = moveableTarget.getBody().getAngle();
//        if (endAngle - startingAngle < 0) {
//
//            if (currentRotation <= endAngle) {
//
//                moveableTarget.getBody().setAngularVelocity(0);
//                moveableTarget.setRotation((float) endAngle);
//                isRotated = true;
//            }
//            if (isRotated && currentRotation > (endAngle - startingAngle) / 2) {
//                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, 0, moveableTarget.getHeight() / 2, true);
//                moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, 0, -moveableTarget.getHeight() / 2, true);
//            } else {
//                isRotated = false;
//                moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, 0, moveableTarget.getHeight() / 2, true);
//                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, 0, -moveableTarget.getHeight() / 2, true);
//            }
//        } else {
//            if (isRotated && currentRotation < (endAngle - startingAngle) / 2) {
//                moveableTarget.getBody().applyForce(-moveableTarget.getRotationSpeed() * delta, 0, 0, moveableTarget.getHeight() / 2, true);
//                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, 0, -moveableTarget.getHeight() / 2, true);
//            } else {
//                isRotated = false;
//                moveableTarget.getBody().applyForce(moveableTarget.getRotationSpeed() * delta, 0, 0, moveableTarget.getHeight() / 2, true);
//                moveableTarget.getBody().applyForce(+moveableTarget.getRotationSpeed() * delta, 0, 0, -moveableTarget.getHeight() / 2, true);
//            }
//            if (currentRotation >= endAngle) {
//                moveableTarget.getBody().setAngularVelocity(0);
//                moveableTarget.setRotation((float) endAngle);
//                isRotated = true;
//            }
//        }
        return false;
    }

    private boolean moveTowards(float delta) {
        float x_inc = (float) (delta * moveableTarget.getAccelerationSpeed() * Math.cos(direction));
        float y_inc = (float) (delta * moveableTarget.getAccelerationSpeed() * Math.sin(direction));
        moveableTarget.getBody().applyForce(x_inc, y_inc, moveableTarget.getBody().getWorldCenter().x, moveableTarget.getBody().getWorldCenter().y, true);

        return false;
    }
}
