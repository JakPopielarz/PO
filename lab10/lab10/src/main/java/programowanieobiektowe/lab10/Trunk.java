package programowanieobiektowe.lab10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Trunk implements XmasShape{
    double scale = 1;
    double x;
    double y;
    int width = 50;
    int height = 25;
    Color fillColor = new Color(102, 51, 0);
    Color lineColor = new Color(153, 77, 0);

    Trunk(){}
    
    Trunk(double scale, double y) {
        this.x = -width/2 * scale;
        this.y = y;
        this.scale = scale;
    }
    
    public double getHeight() { return height*scale; }
    public double getY() { return y; }
    
    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(fillColor);
        // narysuj wypełnienie
        g2d.fillRect(0, 0, width, height);
        // ustaw kolor obramowania
        g2d.setColor(lineColor);
        // narysuj obramowanie
        g2d.drawRect(0, 0, width, height);
    }
 
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
