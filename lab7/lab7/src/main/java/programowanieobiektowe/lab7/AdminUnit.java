package programowanieobiektowe.lab7;

import java.util.List;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
    List<AdminUnit> children;

    public String toString() {
        StringBuilder buf = new StringBuilder();
        
        buf.append(name).append(": ");
        buf.append("admin level: ").append(adminLevel).append("; ");
        buf.append("population: ").append(population).append("; ");
        buf.append("area: ").append(area).append("; ");
        buf.append("density: ").append(density).append("; ");
        
        return buf.toString();
    }
    
    void fixMissingValues() {
        if (density == 0) {
            if (parent != null && parent.density == 0) parent.fixMissingValues();
            this.density = parent.density;
        }
        if (population == 0) population = area*density;
    }
}
