/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author jakub
 */
public class MatrixTest {
    
    public MatrixTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }

    /**
     * Test of asArray method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testAsArray() {
        System.out.println("asArray");
        Matrix instance = new Matrix(new double[][] {{1,2,3,4},{5,6},{7,8},{9}});
        double[][] expResult = new double[][]{{1, 2, 3, 4}, {5, 6, 0, 0}, {7, 8, 0, 0}, {9, 0, 0, 0}};
        double[][] result = instance.asArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of get method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testGet() {
        System.out.println("get");
        int r = 0;
        int c = 0;
        Matrix instance = new Matrix(new double[][] {{1,2,3,4},{5,6},{7,8},{9}});
        double expResult = 0;
        double result = instance.get(3, 3);
        assertEquals(expResult, result);
    }

    /**
     * Test of set method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testSet() {
        System.out.println("set");
        int r = 2;
        int c = 3;
        double value = 42;
        Matrix instance = new Matrix(new double[][] {{1,2,3,4},{5,6},{7,8},{9}});
        instance.set(r, c, value);
        assertEquals(value, instance.get(r, c));
    }

    /**
     * Test of toString method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testToString() {
        System.out.println("toString");
        Matrix instance = new Matrix(new double[][] {{1,2,3,4},{5,6},{7,8},{9}});
        int expResultComas = 12;
        int expResultParenths = 10;
        String strResult = instance.toString();
        int resultComas = 0;
        int resultParenths = 0;
        
        for (int i=0; i<strResult.length(); i++) {
            if (strResult.charAt(i) == ",".charAt(0))
                resultComas++;
            else if (strResult.charAt(i) == "[".charAt(0) ||
                    strResult.charAt(i) == "]".charAt(0))
                resultParenths++;
        }
        assertEquals(expResultComas, resultComas);
        assertEquals(expResultParenths, resultParenths);
    }

    /**
     * Test of reshape method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testReshape() {
        System.out.println("reshape");
        int newRows = 8;
        int newCols = 2;
        Matrix instance = new Matrix(new double[][] {{1,2,3,4},{5,6},{7,8},{9}});
        try {
            instance.reshape(newRows, newCols);
        } catch (RuntimeException exception) {
            fail(exception.toString());
        }
    }

    /**
     * Test of shape method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testShape() {
        System.out.println("shape");
        Matrix instance = new Matrix(52, 12);
        int[] expResult = new int[] {52, 12};
        int[] result = instance.shape();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of add method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testAdd_Matrix() {
        System.out.println("add");
        Matrix m = null;
        Matrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.add(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sub method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testSub_Matrix() {
        System.out.println("sub");
        Matrix m = null;
        Matrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.sub(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mul method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testMul_Matrix() {
        System.out.println("mul");
        Matrix m = null;
        Matrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.mul(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of div method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testDiv_Matrix() {
        System.out.println("div");
        Matrix m = null;
        Matrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.div(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testAdd_double() {
        System.out.println("add");
        double w = 51;
        Matrix instance = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        Matrix expResult = new Matrix(new double[][]{{52, 53, 54, 55}, {56, 57, 51, 51},
                                                     {58, 59, 51, 51}, {60, 51, 51, 51}});
        Matrix result = instance.add(w);
        int r = 2;
        int c = 2;
        assertEquals(expResult.get(r, c), result.get(r, c));
    }

    /**
     * Test of sub method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testSub_double() {
        System.out.println("sub");
        double w = 51;
        Matrix instance = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        Matrix expResult = new Matrix(new double[][]{{-50, -49, -48, -47}, {-46, -45, -51, -51},
                                                     {-44, -43, -51, -51}, {-42, -51, -51, -51}});
        Matrix result = instance.sub(w);
        int r = 2;
        int c = 2;
        assertEquals(expResult.get(r, c), result.get(r, c));
    }

    /**
     * Test of mul method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testMul_double() {
        System.out.println("mul");
        double w = 2.0;
        Matrix instance = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        Matrix expResult = new Matrix(new double[][]{{2, 4, 6, 8}, {10, 12}, {14, 16}, {18}});
        Matrix result = instance.mul(w);
        int r=1;
        int c=1;
        assertEquals(expResult.get(r, c), result.get(r, c));
    }

    /**
     * Test of div method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testDiv_double() {
        System.out.println("div");
        double w = 2.0;
        Matrix instance = new Matrix(new double[][]{{1, 2, 3, 4}, {5, 6}, {7, 8}, {9}});
        Matrix expResult = new Matrix(new double[][]{{0.5, 1, 1.5, 2}, {2.5, 3}, {3.5, 4}, {4.5}});
        Matrix result = instance.div(w);
        int r=1;
        int c=1;
        assertEquals(expResult.get(r, c), result.get(r, c));
    }

    /**
     * Test of dot method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testDot() {
        System.out.println("dot");
        Matrix m = null;
        Matrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.dot(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of frobenius method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testFrobenius() {
        System.out.println("frobenius");
        Matrix instance = null;
        double expResult = 0.0;
        double result = instance.frobenius();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of random method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testRandom() {
        System.out.println("random");
        int rows = 0;
        int cols = 0;
        Matrix expResult = null;
        Matrix result = Matrix.random(rows, cols);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eye method, of class Matrix.
     */
    @org.junit.jupiter.api.Test
    public void testEye() {
        System.out.println("eye");
        int n = 0;
        Matrix expResult = null;
        Matrix result = Matrix.eye(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
