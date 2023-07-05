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
import utils.Menu;

/**
 *
 * @author ASUS
 */
public class Tester {
    public static void main(String[] args){
            Product prd2 = new Product("prd100003", "Bun Bo", 100, 20, 20, "PAAAA", 5.0);
            Product prd3 = new Product("prd100004", "Banh uot", 100, 20, 30, "PAAAA", 2.0);
            Product prd4 = new Product("prd100005", "Banh Mi", 100, 20, 10, "PAAAA", 3.0);
            ProductList prdList= new ProductList();
            prdList.addProduct(prd2);
            prdList.addProduct(prd3);
            prdList.addProduct(prd4);
            prdList.writeProductToList();
              Menu menu = new Menu();
              menu.mainMenu();
    }
}
