package utils;

import entity.*;
import static java.lang.System.out;
import java.util.List;

public class Menu {
    private boolean exit = false;
    private static User currentUser;
    private static ShopOwner currentOwner;
    private final UserList userList = new UserList();
    private final ProductList productList = new ProductList();
    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final ShopOwnerList shopownerList = new ShopOwnerList();
    
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
                    userMenu();
                    break;
                case 2:
                    shopMenu();
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

    public void displayUserMainMenu() {
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
//            userLoggedInMenu();
        }
    }

    public void logoutUser() {
        System.out.println("==== Logout ====");
        currentUser = null;
        mainMenu();
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


//----------------------------------------------------------------------------------------------------------------------

    public void shopMenu() {
        exit = false;
        while (!exit) {
            displayShopMainMenu();
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

    
    public void displayShopMainMenu() {
        System.out.println("==== Shop Menu ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Logout");
    }

    public void registerOwner() {
        System.out.println("==== Register ====");
        shopownerList.registerShopOwner();
    }

    public void loginOwner() {
        System.out.println("==== Login ====");
        currentOwner = shopownerList.loginShopOwner();
        if (currentOwner == null) {
            System.out.println("Your username or password is incorrect. Please enter again or create a new one.");
        } else {
            shopLoggedInMenu();
        }
    }

    public void logoutOwner() {
        System.out.println("==== Logout ====");
        currentOwner = null;
        mainMenu();
    }

    public void shopLoggedInMenu() {
        exit = false;
        while (!exit) {
            displayShopLoggedInMenu();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 5);

            switch (choice) {
                case 1:
                    showProduct();
                    break;
                case 2:
                    addProductToShop();
                    break;
                case 3:
                    removeProductFromShop();
                    break;
                case 4:
                    updateProductInShop();
                    break;
                case 5: 
                    shopMenu();
                    break;
                                        
            }
        }
    }

    public void displayShopLoggedInMenu() {
        System.out.println("==== Owner ====");
        System.out.println("1. Show Product");
        System.out.println("2. Add Product to Shop");
        System.out.println("3. Remove Product from Shop");
        System.out.println("4. Update Product in Shop");
        System.out.println("5. Logout");
    }
    
    public void  showProduct(){
        System.out.println("==== Show Product in Shop ====");
        productList.readFromProductList();
        List<Product> product = productList.toList();
        for(Product prd: product){
            if (prd.getShopowner().equals(currentOwner))
                System.out.println(prd);
            else {
                System.out.println("There are no stores available");
            }
        }     
    }
    
    public void addProductToShop() {
         System.out.println("==== Add Product to Shop ====");
         String productName = MyUtils.inputString("Enter product name: ");
         double price = (double)MyUtils.inputInteger("Enter product price:",0,Integer.MAX_VALUE);
         int quantity = MyUtils.inputInteger("Enter product quantity:",0,Integer.MAX_VALUE);
         double rating = 0.0;
         int soldQuantity = 0;
         
         Product product = new Product(productName, productName, quantity, price, soldQuantity, rating, currentOwner);
         
         productList.readFromProductList();
         productList.addProduct(product);
         productList.writeProductToList();

         System.out.println("Product added to the shop");
    }

    public void removeProductFromShop() {
        System.out.println("==== Remove Product from Shop ====");
        String productID = MyUtils.inputString("Enter product ID to remove: ");
        
        productList.readFromProductList();
        for(Product prd: productList.toList())
        {
            if(prd.getProductID().equals(productID) && prd.getShopowner().equals(currentOwner))
                productList.removeProduct(productID);
            else 
                System.out.println("Product not found in the shop");
        }
    }

    public void updateProductInShop() {
        System.out.println("==== Update Product in Shop ====");
        String productID = MyUtils.inputString("Enter product ID to update: ");
        productList.readFromProductList();
        for(Product prd: productList.toList())
            if(prd.getProductID().equals(productID) && prd.getShopowner().equals(currentOwner))
                productList.updateProduct(productID, prd);
         else {
            System.out.println("Product not found in the shop.");
        }
    }
}
