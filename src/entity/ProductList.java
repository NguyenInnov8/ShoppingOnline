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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductList extends HashMap<String, Product> {
    private static final long serialVersionUID = 1L;

    private String header = "----------------------------------------------------------------------------\n"+
                            "| Code     | Product name         | Quantity | Price | Sold Quantity | Rate |\n"+
                            "|----------|----------------------|----------|-------|---------------|------|";
    private String footer = "----------------------------------------------------------------------------";
    private final static String productFile = "src\\data\\productList.txt";
    public List<Product> toList() {
        return new ArrayList<>(this.values());
    }

    public void addProduct(Product product) {
        this.put(product.getProductID(), product);
    }

    
    public void showAll(List<Product> l) {
        System.out.println(header);
        for (Product product : l) {
            System.out.println(product);
        }
    }


    public void removeProduct(String productID) {
        this.remove(productID);
    }

    public void writeProductToList() {
        File file = new File(productFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try (OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os)) {
            for (Product product : this.values()) {
                oos.writeObject(product);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFromProductList() {
        File file = new File(productFile);
        if (!file.exists()) {
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                try {
                    Product product = (Product) ois.readObject();
                    this.put(product.getProductID(), product);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayAll() {
        System.out.println(header);
        for (Product product : this.values()) {
            System.out.println(product);
        }
        System.out.println(footer);
    }

    public void displayLowQuantityProduct() {
        System.out.println(header);
        for (Product product : this.values()) {
            if (product.getQuantity() < 10) {
                System.out.println(product);
            }
        }
        System.out.println(footer);
    }

    public void displayHighRatingProduct() {
        System.out.println(header);
        for (Product product : this.values()) {
            if (product.getRating() >= 4.5) {
                System.out.println(product);
            }
        }
        System.out.println(footer);
    }

    public boolean isProductIDExist(String productID) {
        return this.containsKey(productID);
    }
    
    public void sortBySoldQuantity() {
        List<Product> toSortList = toList();
        Collections.sort(toSortList, Collections.reverseOrder());
        showAll(toSortList);
    }
    
    public void SortByRating() {
        List<Product> toSortList = toList();
        Collections.sort(toSortList, Product.compareByRatingStar);
        showAll(toSortList);
    }
}
