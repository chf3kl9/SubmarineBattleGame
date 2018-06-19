package game.models.interfaces;

import game.models.Coördinate;
import game.models.Player;
import game.models.Vector;

public interface ISubmarine extends IGameObject{
    ITorpedo fireTorpedo();
    void rotate(int direction);
    void accelerate(int direction);
    void moveStep();
    Player getPlayer();
    Coördinate getCoördinate();
    Vector getEngineVector();
    float getAngle();
    boolean canSee(IGameObject other);
}
