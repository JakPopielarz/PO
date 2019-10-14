package lab1;

import java.util.Scanner;

public class Problem115A {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 1 || n > 2000) {
            return;
        }

        int[] arr = new int[n + 1];
        int managed = 0;
        int result = 0;

        for (int i=1; i<=n; i++) {
            arr[i] = scan.nextInt();

            managed = 0;
            int manager = arr[i];

            while (manager != -1) {
                manager = arr[manager];
                managed += 1;
            }

            result = Math.max(result, managed);
        }
        System.out.println(result + 1);
    }
}
