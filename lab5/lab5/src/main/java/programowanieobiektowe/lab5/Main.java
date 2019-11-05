package programowanieobiektowe.lab5;

import java.util.Locale;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        buildAndPrint();
//        buildAndEvaluate();
        defineCircle();
    }
    
    /*
    * Test 1
    */
    static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }
    
    /*
    * Test 2
    */
    static void buildAndEvaluate(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x,3))
                .add(-2,new Power(x,2))
                .add(-1,x)
                .add(2);
        for(double v=-5; v<5; v+=0.1){
            x.setValue(v);
//            System.out.printf(Locale.US,"f(%f)=%f\n",v,exp.evaluate());
            double value = exp.evaluate();
            if (Math.abs(value) < 1e-7)
                System.out.printf(Locale.US,"f(%f)=%f\n", v, value);
                // Wyniki wyglądają na -0 albo +0, ponieważ nie są dokładnie 0,
                // tylko troszkę więcej lub mniej, a wypisanie tylko zaokrągla
        }
    }
    
    /*
    * Test 3
    */
    static void defineCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x, 2))
                .add(new Power(y, 2))
                .add(8, x)
                .add(4, y)
                .add(16);
        System.out.println(circle.toString());

        Random r = new Random();
        
        for (int i=1; i<=100; i++) { // nie do końca
            double xv = -6 + (-2 + 6) * r.nextDouble();
            double yv = -4 + (0 + 4) * r.nextDouble();
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            System.out.print(String.format("%d Punkt (%f,%f) leży %s koła %s\n", i, xv, yv, (fv <= 0 ? "wewnątrz" : "na zewnątrz"), circle.toString()));
        }
    }
}
