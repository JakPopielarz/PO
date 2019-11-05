package programowanieobiektowe.lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();
 
    Prod(){}
 
    Prod(Node n1){
        args.add(n1);
    }

    Prod(double c){
        args.add(new Constant(c));
    }
 
    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    
    Prod(double c, Node n){
        args.add(new Constant(c));
        args.add(n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }
 
    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }
 
    @Override
    public double evaluate() {
        double result =1;
        
        for (Node arg : args)
            result *= arg.evaluate();
        
        return sign*result;
    }
    
    @Override
    int getArgumentsCount(){return args.size();}
 
    @Override
    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        
        for (int i=0; i<args.size(); i++) {
            Node arg = args.get(i);
            
            if (arg.sign < 0) b.append("(");
            b.append(arg.toString());
            if (arg.sign < 0) b.append(")");
            
            if (i < args.size()-1) b.append("*");
        }
        
        return b.toString();
    }
}
