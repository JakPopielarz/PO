package programowanieobiektowe.lab10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Light implements XmasShape {
    double scale = 1;
    double x;
    double y;
    Bubble lower;
    int lower_diameter = 20;
    int[] vertices_x = new int[4];
    int[] vertices_y = new int[4];
    Color lineColor = new Color(250, 185, 22);
    Color fillColor = new Color(230, 97, 2);
    
    Light(double x, double y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        lower = new Bubble(0, 0, scale, fillColor, fillColor, lower_diameter);
        vertices_x[0] = 0;
        vertices_x[1] = lower_diameter/2;
        vertices_x[2] = 2* lower_diameter/3;
        vertices_x[3] = lower_diameter;
        
        vertices_y[0] = lower_diameter/2;
        vertices_y[1] = -lower_diameter;
        vertices_y[2] = -lower_diameter/2;
        vertices_y[3] = lower_diameter/2;
    }
    
    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.setColor(fillColor);
        //narysuj wypełnienie
        g2d.fillPolygon(vertices_x, vertices_y, 4);
        // ustaw kolor obramowania
        g2d.setColor(lineColor);
        // narysuj obramowanie
        g2d.drawPolygon(vertices_x, vertices_y, 4);
        
        lower.render(g2d);
    }
 
    @Override
    public void transform(Graphics2D g2d) {
//        lower.transform(g2d);
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
