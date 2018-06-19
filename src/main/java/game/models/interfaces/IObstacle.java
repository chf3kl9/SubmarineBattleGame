package game.models.interfaces;

import game.models.Coördinate;

public interface IObstacle extends IGameObject{
    void moveStep();
    Coördinate getRightBottom();
    Coördinate getCoördinate();
}
