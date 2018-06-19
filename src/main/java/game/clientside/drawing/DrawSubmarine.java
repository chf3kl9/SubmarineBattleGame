package game.clientside.drawing;

import game.models.Coördinate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class DrawSubmarine extends JPanel {
    Coördinate c1;
    Coördinate c2;

    public DrawSubmarine(Coördinate c1, Coördinate c2){
        this.c1 = c1;
        this.c2 = c2;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Line2D line = new Line2D.Double(c1.getX(), c1.getY(), c2.getX(), c2.getY());
        g2d.setColor(Color.BLUE);
        g2d.draw(line);
    }
}