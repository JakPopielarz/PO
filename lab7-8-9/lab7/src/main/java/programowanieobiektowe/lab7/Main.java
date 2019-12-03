package programowanieobiektowe.lab7;

import java.io.IOException;
import static java.lang.System.out;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        AdminUnitList unitList = new AdminUnitList();
        
        try {
            unitList.read("admin-units.csv", false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(unitList)
                .where(a -> a.area > 1000)
                .or(a -> a.name.startsWith("Sz"))
                .sort((a, b) -> Double.compare(a.area, b.area))
                .limit(100);
        query.execute().list(out);
        
//        unitList.filter(a->a.name.startsWith("K"), 2, 4).list(out);
        
//        unitList.filter(a->(a.adminLevel==6 && a.parent.name.equals("województwo małopolskie"))).list(out);
        
//        unitList.filter(a->a.name.startsWith("K")).list(out);
        
//        unitList.filter(a->a.name.startsWith("Ż")).sortInplaceByArea().list(out);
        
//        AdminUnit unit = unitList.selectByName("^Kolonia.*", true).units.get(0);
//        
//        double t1 = System.nanoTime()/1e6;
//        
//        unitList.getNeighbors(unit, 15);
//        
//        double t2 = System.nanoTime()/1e6;
//        System.out.printf(Locale.US, "t2-t1=%f\n", t2-t1);
        
//        unitList.list(System.out, 0, 10);
//        unitList.selectByName("^Kolonia.*", true).list(System.out);
    }
}
