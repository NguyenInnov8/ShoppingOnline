package entity;

import java.util.ArrayList;
import java.util.List;

public class ShopOwner extends User {
    private String shopName;
    private final List<Product> products;

    public ShopOwner(String username, String password, String email, String shopName) {
        super(username, password, email);
        this.shopName = shopName;
        this.products = new ArrayList<>();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void addProductToShop(Product product) {
        products.add(product);
        System.out.println("Product added to the shop: " + product.getProductName());
    }

    public void removeProductFromShop(Product product) {
        if (products.remove(product)) {
            System.out.println("Product removed from the shop: " + product.getProductName());
        } else {
            System.out.println("Product not found in the shop.");
        }
    }

    public void updateProductInShop(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == product.getProductID()) {
                products.set(i, product);
                System.out.println("Product updated in the shop: " + product.getProductName());
                return;
            }
        }
        System.out.println("Product not found in the shop.");
    }
}
