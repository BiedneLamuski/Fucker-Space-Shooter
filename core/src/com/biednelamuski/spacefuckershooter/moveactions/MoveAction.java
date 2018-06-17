package com.biednelamuski.spacefuckershooter.moveactions;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.Moveable;
import com.biednelamuski.spacefuckershooter.gameobjects.spaceobjects.SpaceObject;

public abstract class MoveAction extends MoveToAction {

    protected SpaceObject moveableActor;
    protected SpaceObject moveableTarget;

    @Override
    protected void update(float percent) {
        super.update(percent);
    }

    @Override
    public void setActor(Actor actor) {
        if (actor instanceof Moveable) {
            this.moveableActor = (SpaceObject) actor;
            super.setActor(actor);
        } else {
            super.setActor(null);
            System.out.println("Warn trying to move not moveable object");
        }
    }

    @Override
    public void setTarget(Actor target) {
        if (target instanceof Moveable) {
            this.moveableTarget = (SpaceObject) target;
            super.setTarget(target);
        } else {
            super.setTarget(null);
            System.out.println("Warn Target is not moveable");
        }

    }
}
