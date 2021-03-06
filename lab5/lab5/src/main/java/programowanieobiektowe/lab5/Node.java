package programowanieobiektowe.lab5;

abstract public class Node {
    int sign = 1;
    
    Node minus() {
        sign = -1;
        return this;
    }
    
    Node plus() {
        sign = 1;
        return this;
    }
    
    int getSign() { return sign; }
    
    abstract Node diff(Variable var);
    
    abstract boolean isZero();
    
    /*
     * Oblicza wartość wyrażenia dla danych wartości zmiennych
     * występujących w wyrażeniu
     */
    public abstract double evaluate();

    /*
    * Zwraca tekstową reprezentację wyrażenia
    */
    public String toString() { return ""; }
    
    /*
    * Zwraca liczbę argumentów węzła
    */
    int getArgumentsCount() { return 0; }
}
