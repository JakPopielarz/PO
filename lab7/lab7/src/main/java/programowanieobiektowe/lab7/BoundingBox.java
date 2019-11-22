package programowanieobiektowe.lab7;

public class BoundingBox {
    double left;
    double top;
    double right;
    double bottom;
    
    void addPoint(double x, double y) {
        
    }
    
    boolean intersects(BoundingBox other) {
        return (this.right >= other.left &&
                this.left <= other.right &&
                this.bottom <= other.top &&
                this.top >= other.bottom);
    }
    
    boolean contains(BoundingBox other) {
        return false;
    }
}
