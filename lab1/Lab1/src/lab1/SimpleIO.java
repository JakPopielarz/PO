/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.Scanner;
/**
 *
 * @author jakub
 */
public class SimpleIO {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f\n",s,i,d);
    }
}
