/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ShoppingCart {
    private final List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void updateProductQuantity(Product product, int quantity) {
        product.setQuantity(quantity);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public void displayCartItems() {
        System.out.println("Cart Items:");
        for (Product product : products) {
            System.out.println(product.getProductName() + " - Quantity: " + product.getQuantity() + " - Price: $" + product.getPrice());
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
