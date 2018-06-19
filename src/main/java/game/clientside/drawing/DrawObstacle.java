package game.clientside.drawing;

import game.models.Coördinate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawObstacle extends JPanel {
    Coördinate c1;
    int width;
    int height;

    public DrawObstacle(Coördinate c1, Coördinate c2){
        this.c1 = c1;
        width = c2.getX()-c1.getX();
        height = c2.getY()-c1.getY();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rect = new Rectangle2D.Double(c1.getX(), c1.getY(), width, height);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
    }
}