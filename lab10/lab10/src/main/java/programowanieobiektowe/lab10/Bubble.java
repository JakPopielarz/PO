package programowanieobiektowe.lab10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale = 1;
    Color lineColor = new Color(255, 204, 0);
    Color fillColor = new Color(255, 0, 0);
    int diameter = 30;
    
    Bubble(){}
    
    Bubble(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    Bubble(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }
    
    Bubble(int x, int y, double scale, Color lineColor, Color fillColor) {
        this(x, y, scale, lineColor, fillColor, 30);
    }
    
    Bubble(int x, int y, double scale, Color lineColor, Color fillColor, int diameter) {
        this.diameter = diameter;
        this.x = x - (diameter/2);
        this.y = y - (diameter/2);
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(fillColor);
        // narysuj wypełnienie
        g2d.fillOval(0,0,diameter,diameter);
        // ustaw kolor obramowania
        g2d.setColor(lineColor);
        // narysuj obramowanie
        g2d.drawOval(0,0,diameter,diameter);
    }
 
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}