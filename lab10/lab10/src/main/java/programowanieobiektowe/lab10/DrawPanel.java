package programowanieobiektowe.lab10;

import java.awt.BasicStroke;
import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    
    List<XmasShape> shapes = new ArrayList<>();
    int branches_total = 5;
    int tree_height = 0;
    
    DrawPanel() {
        setBackground(new Color(255,255,255));
        Color[] colors = {new Color(125, 52, 235),
        new Color(46, 124, 184), new Color(196, 227, 84),
        new Color(125, 37, 2), new Color(163, 65, 145)};
        Color color;
        
	Random random = new Random();

        for (int i=branches_total; i>1; i--) {
            Branch branch = new Branch(i);
            add(branch);
            
            // Dodawanie światełek
            for (int j=0; j<3; j++) {
                double offset = 0;
                if (j != 0)
                    offset = branch.getWidth()/j;
                
                double light_x = branch.getX() + offset;
                if (offset < branch.getWidth()/2)
                    light_x += branch.getWidth()/(i*2);
                else if (offset > branch.getWidth()/2)
                    light_x -= branch.getWidth()/(i*2);
                
                add(new Light(light_x, branch.getY()+branch.getHeight()/1.2, 0.7));
            }
            
            // Dodawanie bombek
            for (int j=0; j<4+i; j++) {
                
                // wylosowanie współrzędnej y na obszarze  obecnie generowanej gałęzi
                int max_y = (int)branch.getHeight();
                int min_y = max_y/2;
                
                int bubble_y = random.nextInt(max_y - min_y) + min_y;
                // wylosowanie współrzędnej x na obszarze gałęzi i wysokości bubble_y
                int max_x = (int)(branch.getWidth()/branch.getHeight()) * Math.abs(bubble_y) - 25;
                int min_x = - max_x;
                int bubble_x = random.nextInt(max_x - min_x) + min_x;
                
                // wylosowanie koloru z tablicy
                color = colors[random.nextInt(colors.length)];
                
                // dodanie bombki
                add(new Bubble(bubble_x, bubble_y + (int)branch.getY(), 1,
                    color.brighter(), color));
            }
            
            // Dodanie pnia
            tree_height += branch.getHeight() - branch.getY();
        }
        
        Trunk trunk = new Trunk(2, tree_height);
        add(trunk);
        tree_height += trunk.getHeight();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.BLUE.darker().darker();
        Color color2 = Color.WHITE;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
 
        g2d.translate(getWidth()/2, getHeight()-tree_height);
        
        for(XmasShape s: shapes) {
            s.draw(g2d);
        }
    }

    public final DrawPanel add(XmasShape shape) {
        shapes.add(shape);
        return this;
    }
}
