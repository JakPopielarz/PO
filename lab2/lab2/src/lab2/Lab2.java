package lab2;

/**
 *
 * @author jakub
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Matrix m = new Matrix(new double [][] {{1,2,3},{4,5,6},{7,8,9}});
        Matrix row = m.mean(0);
        System.out.println(row);
        
        System.out.println("============");
        
        Matrix col = m.mean(1);
        System.out.println(col);
        
        System.out.println("============");
        
        System.out.println(m.mean(2));
    }
    
}
