package lab2;

/**
 *
 * @author jakub
 */
public class Lab2 {
    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{3,2},{1,5},{4,3}});

        System.out.println(m.frobenius());
    }
}
