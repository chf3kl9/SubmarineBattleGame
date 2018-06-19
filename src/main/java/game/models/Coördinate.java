package game.models;

public class Coördinate {

    private int x;
    private int y;

    public Coördinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void translate(int dx, int dy){
        x+= dx;
        y+= dy;
    }

    public double distance(Coördinate c) {
        double dx = c.getX()-(double)x;
        double dy = c.getY()-(double)y;
        return Math.sqrt(dx*dx+dy*dy);
    }

    public boolean same(Coördinate other){
        return other.getX() == x && other.getY() == y;
    }

    public void correction(Coördinate location, double percentage){
        double dx = location.getX()-(double)x;
        double dy = location.getY()-(double)y;
        x+=dx*percentage;
        y+=dy*percentage;
    }
}
