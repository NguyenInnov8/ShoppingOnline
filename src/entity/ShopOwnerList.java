/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import utils.MyUtils;
import validate.Validation;

/**
 *
 * @author ADMIN
 */
public class ShopOwnerList extends ArrayList<ShopOwner> {
  private static final String shopListFile = "src\\data\\shopList.txt"; 
  private ProductList prdList = new ProductList();

  
   public void writeToShopList() {
      try (FileOutputStream fos = new FileOutputStream(shopListFile);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        for (ShopOwner shopowner : this) {
            oos.writeObject(shopowner);
        }
        oos.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }  

   public void readFromShopList() {
    File f = new File(shopListFile);
        if (!f.canRead()) {
            System.out.println("File cannot be read");
            return;
        }
    try (FileInputStream fis = new FileInputStream(shopListFile);
         ObjectInputStream ois = new ObjectInputStream(fis)) {
        this.clear();
        while (true) {
            try {
                ShopOwner shopowner = (ShopOwner) ois.readObject();
                this.add(shopowner);
            } catch (EOFException e) {
                break; 
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   public ShopOwner loginShopOwner() {
        String username = MyUtils.inputString("Enter Username: ");
        String password = MyUtils.inputString("Enter Password: ");

        this.readFromShopList();

        for (ShopOwner shopowner : this) {
            if (username.equals(shopowner.getUsername()) && password.equals(shopowner.getPassword())) {
                System.out.println("Login Successfully. Welcome "+ shopowner.getUsername());
                return shopowner;
            }
        }
        return null;
    }

    public void registerShopOwner() {
        readFromShopList();
        String username = "";
        String password = "";
        String shopname = "";
        do {
            username = MyUtils.inputString("Enter your username: ");
        } while (!Validation.isValidUsername(username));

        do {
            password = MyUtils.inputString("Enter your password: ");
        } while (!Validation.isValidPassword(password));

        do {
            shopname = MyUtils.inputString("Enter your shop name: ");
        } while (!Validation.isValidFullname(shopname));

        ShopOwner registeredOwner = new ShopOwner(username, password, shopname);
        this.add(registeredOwner);

     
        this.writeToShopList();
        System.out.println("Register Successfully! Please back to the main menu to login");
    }
    
       public void addProductToShop(Product product) {
        readFromShopList(); 
        prdList.addProduct(product);
        writeToShopList(); 
        System.out.println("Product added to the shop: " + product.getProductName());
    } 



    public void updateProductInShop(Product product) {
         readFromShopList(); 
         for (int i = 0; i < prdList.size(); i++) {
            if (prdList.get(i).getProductID().equals(product.getProductID())) {
                prdList.toList().set(i, product); 
                writeToShopList();
                System.out.println("Product updated in the shop: " + product.getProductName());
                return;
             }
         }
         System.out.println("Product not found in the shop.");
        }
    
    public void showAllShopOwner() {
        this.readFromShopList();
        for (ShopOwner shopowner : this) {
            System.out.println(shopowner);
        }
    }
}
