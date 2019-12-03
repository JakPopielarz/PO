package programowanieobiektowe.lab7;

public class BoundingBox {
    boolean empty = true;
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    
    void addPoint(double x, double y) {
        if (empty) {
            empty = false;
            xmin = x;
            xmax = x;
            ymin = y;
            ymax = y;
        } else {
            if (x < xmin)
                xmin = x;
            else if (x > xmax)
                xmax = x;
            if (y < ymin)
                ymin = y;
            else if (y > ymax)
                ymax = y;
        }
    }
    
    boolean intersects(BoundingBox other) {
        return (!empty && !other.empty &&
                this.xmax >= other.xmin &&
                this.xmin <= other.xmax &&
                this.ymax <= other.ymin &&
                this.ymin >= other.ymax);
    }
    
    boolean contains(double x, double y) {
        return (!empty &&
                x >= xmin && x <=xmax &&
                y >= ymin && y <= ymax);
    }
    
    boolean contains(BoundingBox other) {
        return (!empty && !other.empty &&
                this.xmin <= other.xmin &&
                this.xmax >= other.xmax &&
                this.ymin <= other.ymin &&
                this.ymax >= other.ymax);
    }

    BoundingBox add(BoundingBox bb) {
        if (!bb.empty) {
            if (bb.xmin < xmin)
                xmin = bb.xmin;
            if (bb.xmax > xmax)
                xmax = bb.xmax;
            if (bb.ymin < ymin)
                ymin = bb.ymin;
            if (bb.ymax > ymax)
                ymax = bb.ymax;
        }
        
        return this;
    }
    
    boolean isEmpty() {
        return empty;
    }
    
    double getCenterX() {
        if (empty) {
            throw new RuntimeException("Bounding box empty");
        } else
            return (xmax+xmin)/2;
    }
    
    double getCenterY() {
        if (empty) {
            throw new RuntimeException("Bounding box empty");
        } else
            return (ymin+ymax)/2;
    }
    
    double distanceTo(BoundingBox bb) {
        if (empty || bb.empty)
            throw new RuntimeException("One of the bounding boxes is empty");
        else {
            // Preparing for calculations
            double r = 6372.8; // Earth radius in km
            
            double lat1 = getCenterX();
            double lon1 = getCenterY();
            
            double lat2 = bb.getCenterX();
            double lon2 = bb.getCenterY();
            
            // converting to radians
            double dLat = Math.toRadians(lat2-lat1);
            double dLon = Math.toRadians(lon2-lon1);
            lat1 = Math.toRadians(lat1);
            lat2 = Math.toRadians(lat2);
            
            // Haversine formula in action
            double a = Math.pow(Math.sin(dLat/2),2) +
                    Math.pow(Math.sin(dLon/2), 2) * Math.cos(lat1) * Math.cos(lat2);
            double c = 2 * Math.asin(Math.sqrt(a));
            
            return r*c;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        
        buf.append("xmin: ").append(xmin).append("; ");
        buf.append("ymin: ").append(ymin).append("; ");
        buf.append("xmax: ").append(xmax).append("; ");
        buf.append("ymax: ").append(ymax).append("; ");
        
        return buf.toString();
    }
}
