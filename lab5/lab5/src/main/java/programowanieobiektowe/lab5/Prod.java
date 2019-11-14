package programowanieobiektowe.lab5;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();
 
    Prod(){}
 
    Prod(Node n1){
        if (! n1.isZero()) args.add(n1);
    }

    Prod(double c){
        if (c != 0) args.add(new Constant(c));
    }
 
    Prod(Node n1, Node n2){
        if (! (n1.isZero() || n2.isZero())) {
            args.add(n1);
            args.add(n2);
        }
    }
    
    Prod(double c, Node n){
        if (c != 0 && (! n.isZero())) {
            args.add(new Constant(c));
            args.add(n);
        }
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
    Node diff(Variable var) {
        Sum r = new Sum();
        for(int i=0; i<args.size(); i++) { 
            Prod m= new Prod();
            for(int j=0; j<args.size(); j++) {
                Node f = args.get(j);
                if(j==i)m.mul(f.diff(var));
                else m.mul(f);
            }
            if(!m.args.isEmpty()) r.add(m);
        }
        return r;
    }
    
    @Override
    boolean isZero() {
        for (Node n : args) {
            if (n.isZero()) return true;
        }
        return false;
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
