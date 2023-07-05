/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import entity.Product;
import java.util.HashMap;
import java.util.Map;

public class CartManagementImpl implements CartManagement {
    private final Map<Product, Integer> cart;
    
    public CartManagementImpl() {
        cart = new HashMap<>();
    }
    
    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            cart.put(product, currentQuantity + quantity);
        } else {
            cart.put(product, quantity);
        }
        System.out.println("Product added to cart: " + product.getProductName() + " (Quantity: " + quantity + ")");
    }

    @Override
    public void removeFromCart(Product product) {
        cart.remove(product);
        System.out.println("Product removed from cart: " + product.getProductName());
    }

    @Override
    public void updateCart(Product product, int newQuantity) {
        if (cart.containsKey(product)) {
            cart.put(product, newQuantity);
        }
    }
    
    @Override
    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    @Override
    public boolean validateCart() {
        return !cart.isEmpty();
    }

    @Override
    public void checkout() {
        if (validateCart()) {
            double totalPrice = calculateTotalPrice();
            System.out.println("Checkout completed. Total price: " + totalPrice);
        } else {
            System.out.println("No items in cart. Cannot proceed to checkout.");
        }
    }

    @Override
    public void viewCart() {
        if (validateCart()) {
            System.out.println("Cart items:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getProductName() + " (Quantity: " + quantity + ")");
            }
        } else {
            System.out.println("Cart is empty.");
        }
    }

    @Override
    public void addToCart(Product product) {
        addToCart(product, 1);
    }
}
