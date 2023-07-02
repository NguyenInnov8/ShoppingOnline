/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tester;

import entity.Product;
import entity.ProductList;
import entity.User;
import entity.UserList;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Tester {
    public static void main(String[] args){
//            Product prd1 = new Product("PRD1002", "Xa Phong", 100, 20, 40, "PAAAA", 5.0);
            ProductList prdList= new ProductList();
//            prdList.addOne(prd1);
//            prdList.writeProductToList();
            prdList.showAll();
    }
}
