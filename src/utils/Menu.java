/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import entity.User;
import entity.UserList;
import java.util.Scanner;

public class Menu {
    private static User currentUser;
    private UserList userList = new UserList();
    

    public void displayUserMainMenu() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
    }

    public void register() {
        System.out.println("==== Register ====");
        userList.registerUser();
        do {
            System.out.println("Press 1 to back to main menu ");
        } while(!"1".equals(MyUtils.inputString("Enter: ")));
    }

    public void login() {
        System.out.println("==== Login ====");
        // Implement login logic here
    }

    public void logout() {
        System.out.println("==== Logout ====");
        // Implement logout logic here
    }

    public void shopMenu() {
        boolean exit = false;

        while (!exit) {
            displayShopMenu();
            int choice = MyUtils.inputInteger("Enter your choice", 1, 4);

            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Returning to the main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayShopMenu() {
        System.out.println("==== Shop Menu ====");
        System.out.println("1. See All Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. Cart Menu");
        System.out.println("4. Go Back");
    }

    public void displayAllProducts() {
        System.out.println("==== All Products ====");
        // Implement logic to display all products in the shop
    }

    public void addProductToCart() {
        System.out.println("==== Add Product to Cart ====");
        // Implement logic to add a product to the cart
    }

    public void cartMenu() {
        System.out.println("==== Cart Menu ====");
        // Implement logic for the cart menu
    }
}
