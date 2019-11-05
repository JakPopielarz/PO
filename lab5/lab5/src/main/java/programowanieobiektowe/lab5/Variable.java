package programowanieobiektowe.lab5;

public class Variable extends Node {
    String name;
    Double value;
    Variable(String name){
        this.name = name;
    }
    void setValue(double d){
        value = d;
    }
 
 
    @Override
    public double evaluate() {
         return sign*value;
    }
 
 
    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }
 
}
