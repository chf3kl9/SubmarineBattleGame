package game.models.interfaces;

import game.models.Coördinate;

public interface ITorpedo extends IGameObject{

    boolean checkCollision(IGameObject other);
    void moveStep();
    Coördinate getCoördinate();
    float getAngle();
}
