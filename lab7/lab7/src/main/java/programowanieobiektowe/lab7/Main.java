package programowanieobiektowe.lab7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList unitList = new AdminUnitList();
        
        try {
            unitList.read("admin-units.csv");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        unitList.list(System.out, 0, 10);
//        unitList.selectByName("^Kolonia.*", true).list(System.out);
    }
}
