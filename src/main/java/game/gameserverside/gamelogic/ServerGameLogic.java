package game.gameserverside.gamelogic;

import game.models.*;
import game.models.interfaces.IObstacle;
import game.models.interfaces.ISubmarine;
import game.models.interfaces.ITorpedo;
import game.gameserverside.serverhandler.IServerHandlerGame;

import java.util.ArrayList;
import java.util.List;

import static game.gameserverside.TempStaticData.getBase_map;
import static game.gameserverside.TempStaticData.spawnPoints;

public class ServerGameLogic implements IServerGameLogic {

    IServerHandlerGame serverHandler;

    List<ISubmarine> submarines;
    List<IObstacle> obstacles;
    List<ITorpedo> torpedoes = new ArrayList<>();


    final static double TICKS_PER_SECOND = 5;
    final static double SKIP_TICKS = 1000 / TICKS_PER_SECOND;

    final static double SECONDS_PER_SONAR = 5;
    final static double SKIP_TICKS_SONAR = 1000*SECONDS_PER_SONAR;


    public ServerGameLogic(Player[] players, float rotationSpeed, float reloadTime, float acceleration, float torpedoSpeed, float maxSpeed, IServerHandlerGame serverHandler){
        this.serverHandler = serverHandler;
        submarines = new ArrayList<>();
        for(int i = 0; i<players.length; i++)
            if (players[i] != null)
                submarines.add(new Submarine(players[i], spawnPoints[i], rotationSpeed, reloadTime, acceleration, torpedoSpeed, maxSpeed));
        obstacles = getBase_map();
    }

    public List<ISubmarine> getSubmarines(){
        return submarines;
    }

    @Override
    public void run(){
        double next_game_tick = System.currentTimeMillis();
        double next_sonar_tick = System.currentTimeMillis();
        while (!Thread.currentThread().isInterrupted()) {
            if (System.currentTimeMillis() >= next_game_tick) {
                //update everything etc
                for(ISubmarine submarine : submarines){
                    submarine.moveStep();
                }
                for (IObstacle obstacle : obstacles){
                    obstacle.moveStep();
                }
                ArrayList<ITorpedo> torpedoesToRemove = new ArrayList<>();

                for (ITorpedo torpedo : torpedoes){
                    torpedo.moveStep();

                    for (ISubmarine submarine : submarines){
                        if (torpedo.checkCollision(submarine)){
                            torpedoesToRemove.add(torpedo);
                            submarines.remove(submarine);
                        }
                    }
                    for (IObstacle obstacle : obstacles){
                        if (torpedo.checkCollision(obstacle)){
                            torpedoesToRemove.add(torpedo);
                            obstacles.remove(obstacle);
                        }
                    }
                }
                torpedoes.removeAll(torpedoesToRemove);


                next_game_tick = System.currentTimeMillis()+SKIP_TICKS;
            }

            if (System.currentTimeMillis() > next_sonar_tick){
                //sonar ping everyone

                for (ISubmarine submarine : submarines){
                    ArrayList<GameObject> vision = new ArrayList<>();
                    for (ISubmarine sub : submarines){
                        if (sub != submarine){
                            if (submarine.canSee(sub)){
                                vision.add((GameObject)sub);
                            }
                        }
                    }
                    for (IObstacle obstacle : obstacles){
                        if (submarine.canSee(obstacle)){
                            vision.add((GameObject)obstacle);
                        }
                    }
                    for (ITorpedo torpedo : torpedoes){
                        if (submarine.canSee(torpedo)){
                            vision.add((GameObject)torpedo);
                        }
                    }

                    serverHandler.sonarPing(submarine.getPlayer().getSessionId(), vision);
                }


                next_sonar_tick = System.currentTimeMillis()+SKIP_TICKS_SONAR;
            }
        }
    }

    public void fireShot(String playerId) {
        for (int i = 0; i<submarines.size(); i++){
            if (submarines.get(i).getPlayer().getSessionId().equals(playerId)){
                ITorpedo torpedo = submarines.get(i).fireTorpedo();
                torpedoes.add(torpedo);
                ArrayList<Torpedo> torps = new ArrayList<>();
                for (ITorpedo torp : torpedoes){
                    torps.add((Torpedo)torp);
                }
                serverHandler.torpedoConfirm(playerId, torps);
                return;
            }
        }
    }

    public void moveSubmarine(String playerId, int rotate, int accelerate){
        for (int i = 0; i<submarines.size(); i++){
            if (submarines.get(i).getPlayer().getSessionId().equals(playerId)){
                submarines.get(i).accelerate(accelerate);
                submarines.get(i).rotate(rotate);
                serverHandler.moveConfirm(playerId, submarines.get(i));
                return;
            }
        }
    }
}
