package lab1;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 1 || n > 2*Math.pow(10, 9))
            return;

        if (n%2 == 1)
            System.out.println(0);
        else {
            int result = n / 4;
            if (n%4 == 0) result -= 1;

            System.out.println(result);
        }
    }
}
