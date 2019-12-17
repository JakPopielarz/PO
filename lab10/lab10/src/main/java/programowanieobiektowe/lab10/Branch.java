package programowanieobiektowe.lab10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Branch implements XmasShape {

    double scale = 1;
    double x;
    double y;
    int base_width = 100;
    int base_height = 60;
    int[] vertices_x = {0, base_width/2, base_width};
    int[] vertices_y = {base_height, 0, base_height};
    Color fillColor = new Color(0, 102, 34);
    Color lineColor = new Color(0, 179, 60);

    Branch(){}
    
    Branch(double scale) {
        this.x = -base_width / 2D * scale;
        this.y = base_height * (scale - 2);
        this.scale = scale;
    }
    
    public double getHeight() { return base_height*scale; }
    public double getWidth() { return base_width*scale; }
    public double getY() { return y; }
    public double getX() { return x; }
    
    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(fillColor);
        // narysuj wypełnienie
        g2d.fillPolygon(vertices_x, vertices_y, 3);
        // ustaw kolor obramowania
        g2d.setColor(lineColor);
        // narysuj obramowanie
        g2d.drawPolygon(vertices_x, vertices_y, 3);
    }
 
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
