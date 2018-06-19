package game.clientside.clientgamelogic;

import game.clientside.clienthandler.IClientHandlerLogic;
import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.interfaces.ITorpedo;
import game.models.Torpedo;

import java.util.ArrayList;
import java.util.List;

public class ClientGameLogic implements IClientGameLogic {

    private static final double CORRECTION_PERCENTAGE = 0.5d; //1=100%, 0.5 = 50% etc
    private static final double TICKS_PER_SECOND = 5;
    private static final double SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private ISubmarine submarine;
    private List<Torpedo> torpedoes = new ArrayList<>();
    private List<GameObject> vision = new ArrayList<>();

    private int rotate;
    private int accelerate;

    private ISubmarine serverSubmarine;

    private IClientHandlerLogic clientHandler;


    public ClientGameLogic(IClientHandlerLogic clientHandler, ISubmarine submarine){
        this.clientHandler = clientHandler;
        this.submarine = submarine;
    }


    @Override
    public void run() {
        double next_game_tick = System.currentTimeMillis();
        while (!Thread.currentThread().isInterrupted()) {
            if (System.currentTimeMillis() >= next_game_tick) {
                moveSubmarine("", rotate, accelerate);
                        submarine.moveStep();
                if (serverSubmarine != null) {
                    serverSubmarine.moveStep();
                    submarine.getCoördinate().correction(serverSubmarine.getCoördinate(), CORRECTION_PERCENTAGE);
                }
                for (ITorpedo torpedo : torpedoes){
                    torpedo.moveStep();
                }
                List<GameObject> screenList = vision;
                screenList.add((GameObject)submarine);
                screenList.addAll(vision);
                screenList.addAll(torpedoes);
                clientHandler.updateScreen(screenList);

                next_game_tick = System.currentTimeMillis()+SKIP_TICKS;
            }
        }
    }

    //region controlls
    public void setRotate(int direction, boolean released){
        if (!released){
            rotate = direction;
        }
        else{
            rotate = 0;
        }
    }

    public void setAccelerate(int direction, boolean released){
        if (!released){
            accelerate = direction;
        }
        else{
            accelerate = 0;
        }
    }
    //endregion


    public void fireShot(String playerId) {
        if (submarine!= null) {
            ITorpedo torpedo = submarine.fireTorpedo();
            this.torpedoes.add((Torpedo)torpedo);
            clientHandler.fireTorpedo();
        }
    }

    public void moveSubmarine(String playerId, int rotate, int accelerate) {
        if (submarine != null) {
            submarine.accelerate(accelerate);
            submarine.rotate(rotate);
            clientHandler.moveSubmarine(rotate, accelerate);
        }
    }

    @Override
    public void setServerSubmarine(ISubmarine submarine) {
        this.serverSubmarine = submarine;
    }

    @Override
    public void setServerTorpedoes(List<Torpedo> torpedoes) {
        this.torpedoes = torpedoes;
    }

    @Override
    public void setVision(List<GameObject> vision) {
        this.vision = vision;
    }
}
