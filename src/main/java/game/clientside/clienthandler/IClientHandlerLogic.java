package game.clientside.clienthandler;

import game.models.GameObject;

import java.util.List;

public interface IClientHandlerLogic {
    void fireTorpedo();
    void moveSubmarine(int rotate, int accelerate);
    void updateScreen(List<GameObject> objects);
}
