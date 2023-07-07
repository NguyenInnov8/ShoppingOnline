package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart extends HashMap<String, Product> implements Serializable{
    private final static long serialVersionUID = 4390163101870860443L;
    private final static String productInCartFile = "src\\data\\productInCart.txt";
    private ProductList prdList = new ProductList();
    private ShopOwnerList sol = new ShopOwnerList();
    private PurchasedProductList purList = new  PurchasedProductList();
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
        try (FileOutputStream fos = new FileOutputStream(productInCartFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
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
        try (FileInputStream fis = new FileInputStream(productInCartFile);
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
    
    public Product getProduct(String productname) {
        readFromProductCartList();
        for (Product product: toList()) {
            if(product.getProductName().equals(productname)) {
                return product;
            }
        }
        return null;
    }

    public void addProductToCart(User user, String productID, int quantity) {
        readFromProductCartList();
        prdList.readFromProductList();
        for (Product product : prdList.toList()) {
            if (product.getProductID().equals(productID)) {
                product.setQuantity(quantity);
                product.setUser(user);
                this.put(productID, product);
            }
        }
        writeProductToCartList();
    }

    public void removeProduct(String productID) {
        readFromProductCartList();
        this.remove(productID);
        writeProductToCartList();
    }
    
    public void purchaseProduct(List<Product> toPurchaseList) {
        readFromProductCartList();
        purList.readFromProductPurchasedList();
        for (Product product : toPurchaseList) {
            prdList.updateQuantityInShopAfterPurchase(product.getQuantity(), product.getProductID());
            purList.addProduct(product);
            purList.writeProductToPurchasedList();
            this.remove(product.getProductID());
        }
        writeProductToCartList();
        
    }

    public void updateProductQuantity(String productName, int quantity) {
        readFromProductCartList();
        for (Product prd: this.toList()) {
            if(prd.getProductName().equals(productName)) {
                prd.setQuantity(quantity);
            }
        }
        writeProductToCartList();
    }
    
    
    public int getProductInCartQuantity(String productName) {
        int productInCartQuantity = 0;
        prdList.readFromProductList();
        for (Product prd: this.toList()) {
            if(prd.getProductName().equals(productName)) {
                productInCartQuantity = prd.getQuantity();
            }
        }
        return productInCartQuantity;
    }

    public double calculateTotalPrice(User user) {
        totalPrice = 0;
        for (Product product : toList()) {
            if(product.getUser().equals(user))
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    
    public boolean validateCart() {
        return !this.isEmpty();
    }
    
    public boolean isEmp(User user) {
        readFromProductCartList();
        List<Product> l = new ArrayList<>();
        for (Product prd: this.toList()) {
            if(prd.getUser().equals(user))
                l.add(prd);
        }
        return l.isEmpty();
    }

    public void viewCart(User user) {
        readFromProductCartList();
        List<Product> l = toList();
        if (!isEmp(user)) {
            System.out.println("Cart items:");
            for (Product product : l) {
                if(user.equals(product.getUser()))
                System.out.println(product.getProductName() + " - Quantity: " + product.getQuantity() + " - Price: $" + product.getPrice() * product.getQuantity());
                 double totalPrice = calculateTotalPrice(user);
                System.out.println("Total price: $" + totalPrice);
            }
        } else {
            System.out.println("Cart is empty.");
        }
    }
}
