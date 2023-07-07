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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class PurchasedProductList extends HashMap<String, Product>{
    private static final String PurchasedProductListFile = "src\\data\\purchasedProduct.txt";
    private ProductList prdList = new ProductList();
    
    public List<Product> toList() {
        return new ArrayList<>(this.values());
    }
    
    public void addProduct(Product prd) {
        this.put(prd.getProductID(), prd);
    }
    
     public void writeProductToPurchasedList() {
        try (FileOutputStream fos = new FileOutputStream(PurchasedProductListFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Product prd : toList()) {
                oos.writeObject(prd);
            }
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromProductPurchasedList() {
        File f = new File(PurchasedProductListFile);
        if (!f.canRead()) {
            System.out.println("File cannot be read");
            return;
        }
        try (FileInputStream fis = new FileInputStream(PurchasedProductListFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.clear();
            while (true) {
                try {
                    Product prd = (Product) ois.readObject();
                    this.put(prd.getProductID(), prd);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException ex) {
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : toList()) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }
    
    public void viewPurchasedList(User user) {
        readFromProductPurchasedList();
        System.out.println("Cart items:");
            for (Product product : toList()) {
                if(user.equals(product.getUser()))
                System.out.println(product.getProductName() + " - Quantity: " + product.getQuantity() + " - Price: $" + product.getPrice() * product.getQuantity());
            }
            double totalPrice = calculateTotalPrice();
            System.out.println("Total price: $" + totalPrice);
    }
}
