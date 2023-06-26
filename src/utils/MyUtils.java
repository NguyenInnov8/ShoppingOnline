/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class MyUtils {

    public static Scanner sc = new Scanner(System.in);

    public static int inputInteger(String msg, int min, int max) {
        if (min > max) {
            int temp = min;
            min = max; 
            max = temp;
        }
        
        int data = Integer.MIN_VALUE;
        while(true) {
            try {
                if(msg != null) {
                System.out.println(msg);
            }
                data = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            
            if(data >= min && data <= max)
                break;
            System.out.println("Please input a valid value. Value is only in the domain [" + min + ", " + max + "]");
        }
        return data;
    }
    
    public static String inputString(String msg) {
        do { 
            if (msg != null)
                System.out.println(msg);
            String data = sc.nextLine();
            
            if(!data.isEmpty()) {
                return data;
            }
            System.out.println("Value cannot empty");
        } while(true);
    }
}
