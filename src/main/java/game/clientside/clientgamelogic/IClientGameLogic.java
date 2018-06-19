package game.clientside.clientgamelogic;

import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Torpedo;
import game.shared.gamelogic.IGameLogic;

import java.util.List;

public interface IClientGameLogic extends IGameLogic {
    void setServerSubmarine(ISubmarine submarine);
    void setServerTorpedoes(List<Torpedo> torpedoes);
    void setVision(List<GameObject> vision);
    void setRotate(int direction, boolean released);
    void setAccelerate(int direction, boolean released);
}
