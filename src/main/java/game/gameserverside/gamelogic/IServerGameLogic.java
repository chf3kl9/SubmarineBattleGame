package game.gameserverside.gamelogic;

import game.models.interfaces.ISubmarine;
import game.shared.gamelogic.IGameLogic;

import java.util.List;

public interface IServerGameLogic extends IGameLogic {
    List<ISubmarine> getSubmarines();
}
