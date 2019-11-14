package programowanieobiektowe.lab6;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        readAsString();
    }
    
    static void readAsString() {
        CSVReader reader = new CSVReader("with-header1.csv", ";", true);

        while(reader.next()) {
            String id = reader.get(0);
            String imie = reader.get(1); 
            String nazwisko = reader.get(2);
            String ulica = reader.get("ulica");
            String nrdomu = reader.get("nrdomu");
            String nrmieszkania = reader.get("nrmieszkania");
            
            System.out.printf("%s %s %s %s %s %s\n", id, imie, nazwisko,
                                ulica, nrdomu, nrmieszkania);
        }
    }
}
