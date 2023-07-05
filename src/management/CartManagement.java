/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import entity.Product;

public interface CartManagement {
    void addToCart(Product product);
    void removeFromCart(Product product);
    void updateCart(Product product, int newQuantity);
    double calculateTotalPrice();
    boolean validateCart();
    void checkout();
    void viewCart();
}
