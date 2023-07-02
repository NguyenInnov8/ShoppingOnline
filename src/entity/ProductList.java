/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utils.MyUtils;
import validate.Validation;

/**
 *
 * @author ASUS
 */
public class ProductList extends HashMap<String, Product>{
    private static final long serialVersionUID = 1L;
    private String header = "--------------------------------------------------------------------------------\n"+
                            "| Code     | Product name         | Quantity | Price | Sold Quantity | Rate |\n"+
                            "|----------|----------------------|----------|-------|---------------|------|\n";
    private String footer = "----------------------------------------------------------------------------";
    private final static String productFile = "src\\data\\productList.txt";
    public List<Product> toList() {
        return new ArrayList<>(this.values());
    }
    
    public void addOne(Product prd) {
        this.putIfAbsent(prd.getProductID(), prd);
    }
    
    public void showAll() {
        readFromProductList();
        System.out.println(header);
        for (Product prd: toList()) {
            System.out.println(prd);
        }
        System.out.println(footer);
    }
    
    public void writeProductToList() {
        try ( OutputStream os = new FileOutputStream(productFile);  ObjectOutputStream oos = new ObjectOutputStream(os)) {

            for (Product product: toList()) {
                oos.writeObject(product);
            }
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void readFromProductList() {
          File f = new File(productFile);
        if (!f.canRead()) {
            System.out.println("File cannot read");
        }
        try {
            FileInputStream is = new FileInputStream(productFile);
            ObjectInputStream ois = new ObjectInputStream(is);
            this.clear();
            while (true) {
                try {
                    Product prd = (Product) ois.readObject();
                    this.addOne(prd);
                } catch (EOFException e) {
                    break; // Break the loop when EOFException occurs
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException eof) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void createProduct() {
        readFromProductList();
        String prdID = "";
        String prdName = "";
        double price = 0.0;
        int quantity = 0;
        int soldQuantity = 0;
        double rating = 0.0;
        String shopID = "ABC";
        do {            
            prdID = MyUtils.inputString("Enter Product's ID: ");
        } while (!Validation.isValidProductId(prdID));
        
        do {
            prdName = MyUtils.inputString("Enter Product's name: ");
        } while (!Validation.isValidProductName(prdName));
        
        do {
            price = (double) MyUtils.inputInteger("Enter Product's price", 0, 10000000);
        } while (!Validation.isValidProductPrice(price));
        
        do {
            quantity = MyUtils.inputInteger("Enter Product's quantity ", 0, 10000000);
        } while(!Validation.isValidProductQuantity(quantity));
        
        Product registeredProduct = new Product(prdID, prdName, quantity, price, soldQuantity, shopID, rating);
        addOne(registeredProduct);
        writeProductToList();
    }
}
