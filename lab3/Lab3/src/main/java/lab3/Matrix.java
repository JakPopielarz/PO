package lab3;

import java.util.Random;

public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }

    Matrix(double[][] d) {
        rows = d.length;
        cols = 0;

        for (int i=0; i<rows; i++)
            cols = Math.max(cols, d[i].length);

        data = new double[rows*cols];

        for (int i=0; i<d.length; i++)
            for (int j=0; j<d[i].length; j++)
                data[i*cols+j] = d[i][j];
    }

    double[][] asArray() {
        double[][] array_2d = new double[rows][cols];

        for (int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                array_2d[i][j] = data[i*cols+j];

        return array_2d;
    }

    double get(int r, int c){
        return data[r*cols+c];
    }

    void set(int r, int c, double value) {
        data[r*cols+c] = value;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[\n");
        for (int i=0; i<rows; i++) {
            buf.append("[");
            for (int j=0; j<cols; j++) {
                buf.append(data[i*cols+j]);
                if (j<cols-1)
                    buf.append(", ");
            }
            buf.append("]\n");
        }
        buf.append("]");

        return buf.toString();
    }

    void reshape(int newRows, int newCols) {
        if (rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        else{
            rows = newRows;
            cols = newCols;
        }
    }
    
    int[] shape() {
        int[] dimensions = {rows, cols};
        return dimensions;
    }
    
    Matrix add(Matrix m) {
        if (this.rows == m.rows && this.cols == m.cols) {
            Matrix result = new Matrix(rows, cols);
            for (int i=0; i<rows; i++) {
                for (int j=0; j<cols; j++){
                    double value = this.get(i, j) + m.get(i, j);
                    result.set(j, j, value);
                }
            }
            return result;
        } else
            throw new RuntimeException(String.format("%d x %d matrix can't be added to %d x %d matrix",this.rows,this.cols,m.rows,m.cols));
    }
    
    Matrix sub(Matrix m) {
        if (this.rows == m.rows && this.cols == m.cols) {
            Matrix result = new Matrix(rows, cols);
            for (int i=0; i<rows; i++) {
                for (int j=0; j<cols; j++){
                    double value = this.get(i, j) - m.get(i, j);
                    result.set(j, j, value);
                }
            }
            return result;
        } else
            throw new RuntimeException(String.format("%d x %d matrix can't be subtracted from %d x %d matrix",this.rows,this.cols,m.rows,m.cols));
    }
    
    Matrix mul(Matrix m) {
        if (this.rows == m.rows && this.cols == m.cols) {
            Matrix result = new Matrix(rows, cols);
            for (int i=0; i<rows; i++) {
                for (int j=0; j<cols; j++){
                    double value = this.get(i, j) * m.get(i, j);
                    result.set(j, j, value);
                }
            }
            return result;
        } else
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplied by %d x %d matrix",this.rows,this.cols,m.rows,m.cols));
    }
    
    Matrix div(Matrix m) {
        if (this.rows == m.rows && this.cols == m.cols) {
            Matrix result = new Matrix(rows, cols);
            for (int i=0; i<rows; i++) {
                for (int j=0; j<cols; j++){
                    double value = this.get(i, j) / m.get(i, j);
                    result.set(j, j, value);
                }
            }
            return result;
        } else
            throw new RuntimeException(String.format("%d x %d matrix can't be divided by %d x %d matrix",this.rows,this.cols,m.rows,m.cols));
    }
    
    Matrix add(double w) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result.set(i,j, this.get(i,j)+w);
        return result;
    }
    
    Matrix sub(double w) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result.set(i,j, this.get(i,j)-w);
        return result;
    }
    
    Matrix mul(double w) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result.set(i,j, this.get(i,j)*w);
        return result;
    }
    
    Matrix div(double w) {
        Matrix result = new Matrix(this.rows, this.cols);
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                result.set(i,j, this.get(i,j)/w);
        return result;
    }
    
    Matrix dot(Matrix m) {
        if (this.cols != m.rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be dot-multiplied by %d x %d matrix",this.rows,this.cols,m.rows,m.cols));
        
        Matrix result = new Matrix(this.rows, m.cols);
        
        for (int i=0; i<this.rows; i++) {
            for (int j=0; j<m.cols; j++) {
                for(int k=0; k<this.cols; k++)
                    result.set(i, j, result.get(i, j ) + (this.get(i, k)*m.get(k, j)));
            }
        }
        
        return result;
    }
    
    double frobenius() {
        double norm = 0;
        
        for (int i=0; i<rows; i++)
            for (int j=0; j<cols; j++)
                norm += Math.pow(this.get(i, j), 2);
        
        norm = Math.sqrt(norm);
        
        return norm;
    }
    
    public static Matrix random(int rows, int cols) {
        Matrix m = new Matrix(rows, cols);
        Random r = new Random();
        
        for (int i=0; i<m.rows; i++)
            for (int j=0; j<m.cols; j++)
                m.set(i, j, r.nextDouble());
        
        return m;
    }
    
    public static Matrix eye(int n) {
        Matrix m = new Matrix(n, n);
        
        for (int i=0; i<n; i++)
            m.set(i, i, 1);
        
        return m;
    }
}
