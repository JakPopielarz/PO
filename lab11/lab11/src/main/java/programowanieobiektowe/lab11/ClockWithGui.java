package programowanieobiektowe.lab11;

import java.awt.*;
import static java.awt.BasicStroke.*;
import java.awt.geom.*;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ClockWithGui extends JPanel {
 
    LocalTime time = LocalTime.now();
    private ClockThread ct;
 
    public ClockWithGui() {
        super();
        ct = new ClockThread();
        ct.start();
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        
        Graphics2D g2d=(Graphics2D)g;
       
        g2d.translate(getWidth()/2,getHeight()/2);
 
        // narysowanie liczb na tarczy zegara
        for(int i=1;i<13;i++){
            String number = Integer.toString(i);
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0, -120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(number,
                    (int)trg.getX()-g2d.getFontMetrics().stringWidth(number)/2,
                    (int)trg.getY()+g2d.getFontMetrics().getHeight()/2);
        }
        
        // narysowanie kresek na tarczy zegara
        for(int i=1;i<61;i++){
            if (i%5 != 0) {
                AffineTransform saveAT =g2d.getTransform();
                g2d.rotate(2*Math.PI/60*i);
                g2d.drawLine(0, -130, 0, -120);
                g2d.setTransform(saveAT);
            }
        }
        
        // narysowanie wskazówki godzinowej
        AffineTransform saveAT = g2d.getTransform();
        g2d.setStroke(new BasicStroke(3, CAP_ROUND, JOIN_MITER));
        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.drawLine(0,0,0,-70);
        g2d.setTransform(saveAT);
        
        // narysowanie wskazówki minutowej
        g2d.setStroke(new BasicStroke(1, CAP_ROUND, JOIN_MITER));
        g2d.rotate(time.getMinute()*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);
        
        // narysowanie wskazówki sekundowej
        g2d.setColor(Color.RED);
        g2d.rotate(time.getSecond()*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);
    }
    
    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());
 
                try {
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClockWithGui.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint();
            }
        }
    }
}