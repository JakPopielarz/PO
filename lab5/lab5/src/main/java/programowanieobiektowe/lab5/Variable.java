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
    Node diff(Variable var) {
        if(var.name.equals(name))return new Constant(sign);
        else return new Constant(0);
    }
    
    @Override
    boolean isZero() {
        double eval_value = this.evaluate();
        return (eval_value == 0);
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
