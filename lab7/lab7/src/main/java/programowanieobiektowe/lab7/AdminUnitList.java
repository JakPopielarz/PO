package programowanieobiektowe.lab7;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToUnit = new HashMap<>();
    Map<AdminUnit, Long> unitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();
    
    public void read(String filename) throws IOException {
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
            
            units.add(newUnit);
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
}