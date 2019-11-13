package programowanieobiektowe.lab6;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        CSVReader reader;
        try {
            reader = new CSVReader("with-header.csv", ",", true);

            while(reader.next()) {
                System.out.println(Arrays.toString(reader.current));
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
