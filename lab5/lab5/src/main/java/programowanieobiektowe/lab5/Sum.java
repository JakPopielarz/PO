package programowanieobiektowe.lab5;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {
    List<Node> args = new ArrayList<>();
 
    Sum(){}
 
    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
 
    Sum add(Node n){
        args.add(n);
        return this;
    }
 
    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }
 
    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }
 
    @Override
    public double evaluate() {
        double result = 0;
        
        for (Node arg : args) {
            result += arg.evaluate();
        }
        
        return sign*result;
    }
 
    int getArgumentsCount(){return args.size();}
 
    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");
 
        for(int i=0; i<args.size(); i++) {
            Node arg = args.get(i);
            
            if (arg.sign < 0) b.append("(");
            b.append(arg.toString());
            if (arg.sign < 0) b.append(")");
            
            if (i < args.size()-1) b.append(" + ");
        }
 
        if(sign<0)b.append(")");
        return b.toString();
    }
}
