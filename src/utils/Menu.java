package utils;

import entity.OwnerList;
import entity.Owner;
import entity.ProductList;
import entity.ShoppingCart;
import entity.User;
import entity.UserList;
import java.util.ArrayList;
public class Menu {

    private boolean exit = false;
    private static User currentUser;
    private static Owner currentOwner;
    private UserList userList = new UserList();
    private OwnerList ownerList = new OwnerList();
    private ProductList prdList = new ProductList();
    private ShoppingCart sCart = new ShoppingCart();

    public final static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public void displayMainMenu() {
        System.out.println("Welcome to Our Shopping Online - The SOP PE");
        System.out.println("You are a: ");
        System.out.println("1. User.");
        System.out.println("2. Shop Owner.");
        System.out.println("3. Exit");
    }

    public void mainMenu() {
        exit = false;

        while (!exit) {
            displayMainMenu();
            int choice = MyUtils.inputInteger("Enter your choice", 1, 3);
            switch (choice) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    shopLogin();
                    break;
                case 3:
                    System.out.println("Thanks for visiting us");
                    exit = true;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void displayUserMainLogin() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
    }

    public void registerUser() {
        System.out.println("==== Register ====");
        userList.registerUser();
        do {
            System.out.println("Press 1 to back to main menu ");
        } while (!"1".equals(MyUtils.inputString("Enter: ")));
    }

    public void loginUser() {
        System.out.println("==== Login ====");
        currentUser = userList.loginUser();
        if (currentUser == null) {
            System.out.println("Your username or password is incorrect. Please enter again or create a new one.");
        } else {
            userMenu();
        }
    }

    public void logoutUser() {
        System.out.println("==== Logout ====");
        currentUser = null;
        mainMenu();
    }

    public void displayUserMainMenu() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Show Store List");
        System.out.println("2. View Cart");
        System.out.println("3. Logout");
    }
    
    public void ShowStoreList() {
       System.out.println("=== Store List ===");

       // Lấy danh sách cửa hàng từ nguồn dữ liệu
       ArrayList<Owner> stores = currentOwner.getShop().getStoreList();

       // Kiểm tra xem có cửa hàng nào trong danh sách hay không
       if (stores.isEmpty()) {
       System.out.println("There are no stores available.");
       } else {
        // Hiển thị danh sách cửa hàng
        for (Store store : stores) {
            System.out.println(store.getName());
           }
        }
    }

    public void ViewCart() {
       System.out.println("=== View Cart ===");

       // Lấy thông tin giỏ hàng từ nguồn dữ liệu
       ArrayList<CartItem> items = sCart.getItems();

       // Kiểm tra xem giỏ hàng có sản phẩm nào hay không
       if (items.isEmpty()) {
          System.out.println("Your cart is empty.");
       } else {
       // Hiển thị thông tin giỏ hàng
       for (CartItem item : items) {
            System.out.println(item.getProduct().getName() + " - Quantity: " + item.getQuantity());
            }
       }
    }

    public void userLogin() {
        exit = false;
        while (!exit) {
            displayUserMainLogin();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 3);

            switch (choice) {
                case 1:
                    ShowStoreList();
                    break;
                case 2:
                    ViewCart();
                    break;
                case 3:
                    logoutUser();
                    break;
            }
        }
    }
    
    public void userMenu() {
        exit = false;
        while (!exit) {
            displayUserMainMenu();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 3);

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    logoutUser();
                    break;
            }
        }
    }
    
//    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     public void registerOwner() {
        System.out.println("==== Register ====");
        ownerList.registerOwner();
        do {
            System.out.println("Press 1 to back to main menu ");
        } while (!"1".equals(MyUtils.inputString("Enter: ")));
    }

    public void loginOwner() {
        System.out.println("==== Login ====");
        currentOwner = ownerList.loginOwner();
        if (currentOwner == null) {
            System.out.println("Your username or password is incorrect. Please enter again or create a new one.");
        } else {
            shopLogin();
        }
    }
    public void logoutOwner() {
        System.out.println("==== Logout ====");
        currentOwner = null;
        mainMenu();
    }
    public void shopLogin() {
         exit = false;
        while (!exit) {
            displayUserMainLogin();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 3);

            switch (choice) {
                case 1:
                    registerOwner();
                    break;
                case 2:
                    loginOwner();
                    break;
                case 3:
                    logoutOwner();
                    break;
            }
        }
    }

}
