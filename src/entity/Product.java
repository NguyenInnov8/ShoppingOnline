/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

<<<<<<< HEAD
/**
 *
 * @author ADMIN
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
=======
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Product implements Serializable{
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
>>>>>>> 2a6a762303e3a5e316217fe012ade5d5135ad7e9
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
=======
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
>>>>>>> 2a6a762303e3a5e316217fe012ade5d5135ad7e9
    }
}
