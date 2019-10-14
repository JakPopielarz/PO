/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author jakub
 */

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("n: ");
        int n = scan.nextInt();

        if (n < 1 || n > 45) return;
        
        int[] tab = new int[n];
        
        System.out.println("-----------");
        tab[0] = 0;
        tab[1] = 1;
        System.out.printf("%d\n%d\n", tab[0], tab[1]);
        
        for (int i=2; i<n; i++){
            tab[i] = tab[i-1] + tab[i-2];
            System.out.println(tab[i]);
        }
    }
}
