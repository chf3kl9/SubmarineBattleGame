package game.shared.gamelogic;

public interface IGameLogic extends Runnable {
    void fireShot(String playerId);
    void moveSubmarine(String playerId, int rotate, int accelerate);
}
