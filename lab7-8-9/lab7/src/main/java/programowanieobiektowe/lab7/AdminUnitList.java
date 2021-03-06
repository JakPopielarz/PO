package programowanieobiektowe.lab7;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToUnit = new HashMap<>();
    Map<AdminUnit, Long> unitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();
    
    public void read(String filename, boolean mapCoords) throws IOException {
        CSVReader reader = new CSVReader(filename);
        boolean hasParent = false;
        while(reader.next()) {
            AdminUnit newUnit = new AdminUnit();
            
            newUnit.name = reader.get("name");
            newUnit.adminLevel = reader.getInt0("admin_level");
            newUnit.population = reader.getDouble0("population");
            newUnit.area = reader.getDouble0("area");
            newUnit.density = reader.getDouble0("density");
            
            idToUnit.put(reader.getLong(0), newUnit);
            try {
                hasParent = true;
                unitToParentId.put(newUnit, reader.getLong("parent"));
            } catch (NumberFormatException e) {
                hasParent = false;
                unitToParentId.put(newUnit, null);
            }
            if (hasParent) {
                try {
                    parentIdToChildren.get(reader.getLong("parent")).add(newUnit);
                } catch (NullPointerException ex) {
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(newUnit);
                    parentIdToChildren.put(reader.getLong("parent"), children);
                }
            }
            
            try {
                newUnit.bbox.addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
                newUnit.bbox.addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
                newUnit.bbox.addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
                newUnit.bbox.addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
            } catch (NumberFormatException ex) {}
            
            units.add(newUnit);
            
            if (mapCoords) {
                System.out.printf(Locale.US," %s: LINESTRING(%f %f, %f %f, %f %f, %f %f,%f %f)\n", 
                reader.get("name"),
                reader.getDouble0("x1"),
                reader.getDouble0("y1"),
                reader.getDouble0("x2"),
                reader.getDouble0("y2"),
                reader.getDouble0("x3"),
                reader.getDouble0("y3"),
                reader.getDouble0("x4"),
                reader.getDouble0("y4"),
                reader.getDouble0("x1"),
                reader.getDouble0("y1")
                );
            }
        }
        
        for (AdminUnit unit: units) {
            unit.parent = idToUnit.get(unitToParentId.get(unit));
        }
        
        for (long id: parentIdToChildren.keySet()) {
            idToUnit.get(id).children = parentIdToChildren.get(id);
        }
        
        fixMissingValues();
    }
    
    void list(PrintStream out) {
        for (AdminUnit unit: units)
            out.println(unit);
    }
    
    void list(PrintStream out, int offset, int limit) {
        for (int i=offset; i<limit; i+=1) {
            out.println(units.get(i));
        }
    }
    
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        
        for (AdminUnit unit: units) {
            if (regex) {
                if (unit.name.matches(pattern))
                    ret.units.add(unit);
            } else {
                if (unit.name.contains(pattern))
                    ret.units.add(unit);
            }
        }
        
        return ret;
    }
    
    private void fixMissingValues() {
        for (AdminUnit unit: units)
            unit.fixMissingValues();
    }
    
    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) {
        AdminUnitList neighbors = new AdminUnitList();
        if (unit.adminLevel > 8)  {// unit jest większy niż miasteczko
            for (AdminUnit potentialNeighbor : units) {
                if (potentialNeighbor.adminLevel == unit.adminLevel &&
                            unit.bbox.intersects(potentialNeighbor.bbox))
                    neighbors.units.add(potentialNeighbor);
            }

            return neighbors;
        } else { // unit jest co najwyżej miasteczkiem
            for (AdminUnit potentialNeighbor : units) {
                if (potentialNeighbor.adminLevel == unit.adminLevel &&
                        unit.bbox.distanceTo(potentialNeighbor.bbox) <= maxdistance)
                    neighbors.units.add(potentialNeighbor);
            }
            return neighbors;
        }
    }
    
    class Com implements Comparator<AdminUnit> {

        @Override
        public int compare(AdminUnit t, AdminUnit t1) {
            if (t.name.compareTo(t1.name) > 0) return 1;
            else if (t.name.compareTo(t1.name) == 0) return 0;
            else return -1;
        }        
    }
    
    AdminUnitList sortInplaceByName() {
        Com com = new Com();
        units.sort(com);
        return this;
    }

    
    AdminUnitList sortInplaceByArea() {
        Comparator<AdminUnit> com = new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit u, AdminUnit u2) {
                if (u.area < u2.area) return 1;
                else if (u.area == u2.area) return 0;
                else return -1;
            }
        };
        
        units.sort(com);
        
        return this;
    }
    
    AdminUnitList sortInplaceByPopulation() {
        Comparator<AdminUnit> com = (AdminUnit u, AdminUnit u2) -> {
            if (u.population < u2.population) return 1;
            else if (u.population == u2.population) return 0;
            else return -1;
        };
        
        units.sort(com);
        
        return this;
    }
    
    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        boolean sorted = false;
        AdminUnit temp;
        
        while(!sorted) {
            sorted = true;
            for (int i=0; i<units.size() - 1; i++) {
                if (cmp.compare(units.get(i), units.get(i+1)) > 0) {
                    temp = units.get(i);
                    units.set(i, units.get(i+1));
                    units.set(i+1, temp);
                    sorted = false;
                }
            }
        }
        
        return this;
    }
    
    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList result = new AdminUnitList();
        
        result.units = this.units;
        return result.sortInplace(cmp);
    }
    
    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList result = new AdminUnitList();

        for (AdminUnit unit: units) {
            if (pred.test(unit)) result.units.add(unit);
        }
        
        return result;
    }
    
    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        AdminUnitList result = new AdminUnitList();

        for (AdminUnit unit: units) {
            if (pred.test(unit) && result.units.size() < limit) result.units.add(unit);
        }
        
        return result;
    }
    
    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList result = new AdminUnitList();

        for (int i=offset; i<units.size(); i++) {
            if (pred.test(units.get(i)) && result.units.size() < limit) 
                result.units.add(units.get(i));
            else if (result.units.size() >= limit)
                break;
        }
        
        return result;
    }
}
