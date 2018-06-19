package game.models.interfaces;

import game.models.Coördinate;

public interface IGameObject {
    void moveStep();
    Coördinate getCoördinate();
}
