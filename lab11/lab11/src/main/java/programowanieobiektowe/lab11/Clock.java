package programowanieobiektowe.lab11;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clock extends Thread {
    @Override
    public void run() {
        while(true) {
            LocalTime time = LocalTime.now();
            System.out.printf("%02d:%02d:%02d\n",
                time.getHour(),
                time.getMinute(),
                time.getSecond());
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
    public static void main(String[] args) {
        new Clock().start();
    }

}
