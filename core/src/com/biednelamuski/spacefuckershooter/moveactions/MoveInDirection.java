package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.biednelamuski.spacefuckershooter.Utils.CommonMath;
import com.biednelamuski.spacefuckershooter.gameobjects.GameWorld;

/**
 * Created by deamo on 24.03.2018.
 */

public class MoveInDirection extends MoveAction {

    private static final float MINIMAL_SPEED = 0.01f;
    private static final float MINIMAL_ROTATION_SPEED = 0.01f;
    private double desX;
    private double desY;
    private double startX;
    private double startY;


    private double direction;
    private double endAngle;
    private double degreesToRotate;
    private double startingAngle;
    private boolean isRotated;
    private double distance;

    private RotationDirection rotationDirection;
    private boolean destinationReached = false;

    private enum RotationDirection {
        LEFT,
        RIGHT;
    }


    public MoveInDirection(double x, double y) {
        this.desX = x;
        this.desY = y;
    }

    @Override
    public void setActor(Actor actor) {
        super.setActor(actor);
        if (actor == null) {
            return;
        }

        startX = actor.getX(Align.center);
        startY = actor.getY(Align.center);

        direction = CommonMath.calculateDirection(desX - startX, desY - startY);
        endAngle = CommonMath.calculateRotation(desX - startX, desY - startY);
        distance = CommonMath.calculateDistance(desX, desY, startX, startY);

        startingAngle = moveableTarget.getBody().getAngle() * MathUtils.radiansToDegrees;
        System.out.println("Starting angle before correction" + startingAngle);
        determineRotationRirection();

        System.out.println("Starting angle: " + startingAngle + "\tEnd angle: " + endAngle + "\tdegrees to rotate: " + degreesToRotate + "rotation direction: " + rotationDirection);
    }

    private void determineRotationRirection() {
        double rotation = (endAngle - startingAngle + 360) % 360;
        rotationDirection = ((rotation <= 180) && (rotation >= 0)) ? RotationDirection.LEFT : RotationDirection.RIGHT;

        degreesToRotate = rotation < 180 ? rotation : -180 + rotation % 180;
    }

    @Override
    public boolean act(float delta) {
        return destinationReached || (rotateTowards(delta) && moveTowards(delta));
    }

    private boolean rotateTowards(float delta) {

        if (isRotated) {
            return true;
        }
        final int direction = rotationDirection == RotationDirection.RIGHT ? 1 : -1;
        final double currentRotation = Math.abs(moveableTarget.getBody().getAngle() * MathUtils.radiansToDegrees - startingAngle);
        final float xPos = moveableTarget.getBody().getWorldCenter().x;
        final float yPos = moveableTarget.getBody().getWorldCenter().y;


        System.out.println(rotationDirection + "\tCurrentSpeed: " + moveableTarget.getBody().getAngularVelocity() + "\tCurrent angle: " + currentRotation + "/" + -direction * degreesToRotate);
        if ((currentRotation + moveableTarget.getBody().getAngularVelocity()) < -direction * (degreesToRotate / 2)) {
            moveableTarget.getBody().applyForce(direction * moveableTarget.getRotationSpeed() * delta, 0, xPos + moveableTarget.getWidth() / 2, yPos + moveableTarget.getHeight(), true);
            moveableTarget.getBody().applyForce(-direction * moveableTarget.getRotationSpeed() * delta, 0, xPos + moveableTarget.getWidth() / 2, yPos, true);
        } else if (currentRotation >= -direction * (degreesToRotate / 2)) {
            if ((currentRotation + moveableTarget.getBody().getAngularVelocity()) < -direction * degreesToRotate) {
                //0.1 -> 0.05 -> 0.01 -> -0.05
                //-0.1 -> -0.05 -> -0.01 -> 0.05...
                //if( 15  )

                if (Math.abs(moveableTarget.getBody().getAngularVelocity()) < MINIMAL_ROTATION_SPEED) {
                    moveableTarget.getBody().setAngularVelocity(-direction * MINIMAL_ROTATION_SPEED);
                } else {
                    moveableTarget.getBody().applyForce(-direction * moveableTarget.getRotationSpeed() * delta, 0, xPos + moveableTarget.getWidth() / 2, yPos + moveableTarget.getHeight(), true);
                    moveableTarget.getBody().applyForce(direction * moveableTarget.getRotationSpeed() * delta, 0, xPos + moveableTarget.getWidth() / 2, yPos, true);
                }
            } else {
                isRotated = true;
                moveableTarget.getBody().setAngularVelocity(0);
            }
        }
        return false;
    }

    private boolean moveTowards(float delta) {

        if (destinationReached) return true;
        float x_inc = (float) (delta * moveableTarget.getAccelerationSpeed() * Math.cos(direction));
        float y_inc = (float) (delta * moveableTarget.getAccelerationSpeed() * Math.sin(direction));

        double currentDistance = CommonMath.calculateDistance(startX, startY, moveableTarget.getX(Align.center), moveableTarget.getY(Align.center));
        float xSpeed = moveableTarget.getBody().getLinearVelocity().x;
        float ySpeed = moveableTarget.getBody().getLinearVelocity().y;

        System.out.print("Distance: " + Math.abs(currentDistance) + "/" + Math.abs(distance) + "\tdestinationReached: " + destinationReached);
        if (Math.abs(currentDistance) < Math.abs(distance) / 2) {
            System.out.print("\tAccelerating");
            moveableTarget.getBody().applyForce(x_inc, y_inc, moveableTarget.getBody().getWorldCenter().x, moveableTarget.getBody().getWorldCenter().y, true);
        } else if (Math.abs(currentDistance) < Math.abs(distance)) {
            System.out.print("\tStopping");

            if (Math.abs(moveableTarget.getBody().getPosition().x + xSpeed) >= Math.abs(desX)) {
                xSpeed = 0f;
//                moveableTarget.getBody().setTransform((float) desX/GameWorld.scaleFactor, moveableTarget.getBody().getPosition().y, moveableTarget.getBody().getAngle());
                x_inc = 0f;
            } else if (Math.abs(xSpeed) < MINIMAL_SPEED) {
                xSpeed =Math.signum(xSpeed) * MINIMAL_SPEED;
                x_inc = 0f;
            }
            if (Math.abs(moveableTarget.getBody().getPosition().y + xSpeed) >= Math.abs(desY)) {
                ySpeed = 0f;
//                moveableTarget.getBody().setTransform(moveableTarget.getBody().getPosition().x, (float) desY/ GameWorld.scaleFactor, moveableTarget.getBody().getAngle());
            } else if (Math.abs(ySpeed) < MINIMAL_SPEED) {
                ySpeed = Math.signum(ySpeed) * MINIMAL_SPEED;
            }
            if(Math.abs(xSpeed) <= MINIMAL_SPEED)
            {
                x_inc = 0f;
            }
            if(Math.abs(ySpeed) <= MINIMAL_SPEED)
            {
                y_inc = 0f;
            }

            System.out.println(" xSpeed: " + xSpeed + "\tySpeed: " + ySpeed + "\tx_inc:" + x_inc + "\ty_inc:" + y_inc);
            moveableTarget.getBody().setLinearVelocity(xSpeed, ySpeed);
            moveableTarget.getBody().applyForce(-x_inc, -y_inc, moveableTarget.getBody().getWorldCenter().x, moveableTarget.getBody().getWorldCenter().y, true);

        } else {
            System.out.println("Stop");
            destinationReached = true;
            moveableTarget.getBody().setLinearVelocity(0f, 0f);
            moveableTarget.getBody().setAngularVelocity(0f);
            return true;
        }


        return destinationReached;
    }
}
