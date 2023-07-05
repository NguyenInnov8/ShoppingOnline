/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class ShoppingCart extends HashMap<String, Product> {

    private final static String productInCartFile = "src\\data\\productInCart.txt";
    private ProductList prdList = new ProductList();
    private double totalPrice = 0;

    public ShoppingCart() {
    }

    public ShoppingCart(User user, double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> toList() {
        return new ArrayList<>(this.values());
    }

    public void writeProductToCartList() {
        try ( FileOutputStream os = new FileOutputStream(productInCartFile);  ObjectOutputStream oos = new ObjectOutputStream(os)) {

            for (Product prd : toList()) {
                oos.writeObject(prd);
            }
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromProductCartList() {
        File f = new File(productInCartFile);
        if (!f.canRead()) {
            System.out.println("File cannot be read");
            return;
        }
        try ( FileInputStream is = new FileInputStream(productInCartFile);  ObjectInputStream ois = new ObjectInputStream(is)) {
            this.clear();
            while (true) {
                try {
                    Product prd = (Product) ois.readObject();
                    this.put(prd.getProductID(), prd);
                } catch (EOFException e) {
                    break; // Break the loop when EOFException occurs
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (EOFException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProductToCart(User user, String productID, int Quantity) {
        readFromProductCartList();
        prdList.readFromProductList();
        for (Product product : prdList.toList()) {
            if (product.getProductID().equals(productID)) {
                product.setQuantity(Quantity);
                product.setUser(user);
                this.put(productID, product);
            }
        }
        writeProductToCartList();
    }

//    public void addProduct(Product product) {
//        this.put(product.getProductID(), product);
//    }
    public void removeProduct(Product product) {
        this.remove(product.getProductID);
    }

    public void updateProductQuantity(Product product, int quantity) {
        product.setQuantity(quantity);
    }

    public double calculateTotalPrice() {
        for (Product product : toList()) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public void displayCartItems(User user) {
        readFromProductCartList();
        List<Product> l = toList();
        System.out.println("Cart Items:");
        for (Product product : l) {
            if(user.equals(product.getUser()))
                System.out.println(product.getProductName() + " - Quantity: " + product.getQuantity() + " - Price: $" + product.getPrice() * product.getQuantity());
        }
    }
}
