package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.math.GridPoint2;

public interface Moveable {

    void update();
    void moveTo(GridPoint2 point);
    void setPosition(GridPoint2 point);
    void stopMoving();
    GridPoint2 getCurrentPosition();

}
