/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author ASUS
 */
public class Product implements Serializable, Comparable {

    private static final long serialVersionUID = 7247210392003788145L;
    private String productID;
    private String productName;
    private int quantity;
    private double price;
    private int soldQuantity;
    private String shopId;
    private double rating;

    public Product() {
    }

    public Product(String productID, String productName, int quantity, double price, int soldQuantity, String shopId, double rating) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.soldQuantity = soldQuantity;
        this.shopId = shopId;
        this.rating = rating;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return String.format("|%6s|%-25s|%5d|%6.1f|%6d|%6s|%6.1f",
                this.productID, this.productName, this.quantity,
                this.price, this.soldQuantity, this.shopId, this.rating);
    }

    @Override
    public int compareTo(Object o) {
        Product toComaprePrd = (Product) o;
        if (this.soldQuantity == toComaprePrd.soldQuantity) {
            return 0;
        } else if (this.soldQuantity > toComaprePrd.soldQuantity) {
            return 1;
        } else {
            return -1;
        }
    }

    public static Comparator<Product> compareByRatingStar = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            // Reverse the comparison by switching the order of o1 and o2
            if (o1.rating == o2.rating) {
                return 0;
            } else if (o1.rating > o2.rating) {
                return -1; // Return -1 instead of 1 to sort in descending order
            } else {
                return 1; // Return 1 instead of -1 to sort in descending order
            }
        }
    };

}
