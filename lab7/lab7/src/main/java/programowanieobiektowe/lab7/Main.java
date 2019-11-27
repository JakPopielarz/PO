package programowanieobiektowe.lab7;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        AdminUnitList unitList = new AdminUnitList();
        
        try {
            unitList.read("admin-units.csv", false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        AdminUnit unit = unitList.selectByName("^Kolonia.*", true).units.get(0);
        
        double t1 = System.nanoTime()/1e6;
        
        unitList.getNeighbors(unit, 15);
        
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US, "t2-t1=%f\n", t2-t1);
        
//        unitList.list(System.out, 0, 10);
//        unitList.selectByName("^Kolonia.*", true).list(System.out);
    }
}
