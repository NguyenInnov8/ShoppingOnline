/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class PurchasedProduct extends Product implements Serializable{
    private boolean isPurchased;

    public PurchasedProduct() {
    }
    

    public PurchasedProduct(boolean isPurchased, String productID, String productName, int quantity, double price, int soldQuantity, double rating, ShopOwner shopowner) {
        super(productID, productName, quantity, price, soldQuantity, rating, shopowner);
        this.isPurchased = isPurchased;
    }

    public boolean isIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}
