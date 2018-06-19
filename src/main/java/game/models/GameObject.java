package game.models;

import game.models.interfaces.IGameObject;

public abstract class GameObject implements IGameObject {
    Coördinate coördinate;
    Vector velocityVector;

    public Coördinate getCoördinate() {
        return coördinate;
    }

    public Vector getVelocityVector() {
        return velocityVector;
    }
}
