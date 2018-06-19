package game.gameserverside;

import game.models.Coördinate;
import game.models.interfaces.IObstacle;
import game.models.Obstacle;
import java.util.ArrayList;

public class TempStaticData {
    /*
    This class holds data that currently is predetermined.
    This class will be deprecated when a method to create maps is made.
     */

    public final static Coördinate[] spawnPoints = {new Coördinate(10, 10), new Coördinate(10, 990),
                                         new Coördinate(990, 10), new Coördinate(990, 990),
                                         new Coördinate(10, 500), new Coördinate(990, 500),
                                         new Coördinate(500, 10), new Coördinate(500, 990)};


    public static ArrayList<IObstacle> getBase_map(){
        ArrayList<IObstacle> base_map = new ArrayList<IObstacle>();
        base_map.add(new Obstacle(new Coördinate(200, 200), new Coördinate(300, 300), true, 2f));
        base_map.add(new Obstacle(new Coördinate(100, 100), new Coördinate(150, 200), true, 2f));
        base_map.add(new Obstacle(new Coördinate(0, 0), new Coördinate(25, 25), true, 2f));
        base_map.add(new Obstacle(new Coördinate(500, 500), new Coördinate(525, 750), true, 2f));
        return base_map;

    }
}
