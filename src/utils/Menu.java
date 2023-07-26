package utils;

import entity.*;
import java.io.IOException;
import static java.lang.System.out;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import validate.Validation;

public class Menu {

    private boolean exit = false;
    private static User currentUser;
    private static ShopOwner currentOwner;
    private final UserList userList = new UserList();
    private final ProductList productList = new ProductList();
    private final ShoppingCart shoppingCart = new ShoppingCart();
    private final ShopOwnerList shopownerList = new ShopOwnerList();
    private final PurchasedProductList purchasedList = new PurchasedProductList();

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
    }

    public void loginUser() {
        System.out.println("==== Login ====");
        currentUser = userList.loginUser();
        if (currentUser == null) {
            System.out.println("Your username or password is incorrect. Please enter again or create a new one.");
        } else {
            userLoggedInMenu();
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

    public void userLoggedInMenu() {
        exit = false;
        while (!exit) {
            displayUserLoggedInMenu();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 5);
            switch (choice) {
                case 1:
                    displayAllShopList();
                    break;
                case 2:
                    showAllProduct();
                    break;
                case 3:
                    addProductToCart();
                    break;
                case 4:
                    cartView();
                    break;
                case 5:
                    logoutUser();
                    break;
            }
        }
    }

    public void displayAllShopList() {
        shopownerList.showAllShopOwner();
        String enteredShopName = MyUtils.inputString("Type Shop name that you want to view: ");
        shopownerList.readFromShopList();
        for (ShopOwner so : shopownerList) {
            if (so.getShopName().equals(enteredShopName)) {
                currentOwner = so;
            }
        }
    }

    public void displayUserLoggedInMenu() {
        System.out.println("1. Show all available Shop and choose a shop");
        System.out.println("2. Show all Product");
        System.out.println("3. Add Product To Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Logout");
    }

    public void showAllProduct() {
        if (currentOwner != null) {
            productList.readFromProductList();
            List<Product> l = new ArrayList<>();
            for (Product prd : productList.toList()) {
                if (prd.getShopowner().equals(currentOwner)) {
                    l.add(prd);
                }
            }
            productList.showAll(l);
        } else {
            System.out.println("You must choose a shop first before requesting show all product");
        }
    }

    public void addProductToCart() {
        shoppingCart.readFromProductCartList();
        int productQuantity = 0;
        showAllProduct();
        String productID = MyUtils.inputString("Enter Product ID to add to cart: ");
        productList.readFromProductList();
        for (Product prd : productList.toList()) {
            if (prd.getProductID().equals(productID) && prd.getShopowner().equals(currentOwner)) {
                productQuantity = prd.getQuantity();
            }
        }
        int inputQuantity = MyUtils.inputInteger("Enter quantity to add to cart: ", 0, productQuantity);
        shoppingCart.addProductToCart(currentUser, productID, inputQuantity);
        System.out.println("Add Successfully!");
    }

    public void displayCartView() {
        System.out.println("==== Cart ====");
        System.out.println("1. Show all Product in Cart");
        System.out.println("2. Update Cart");
        System.out.println("3. Purchcase product");
        System.out.println("4. Rate Product");
        System.out.println("5. Back");
    }

    public void cartView() {
        exit = false;
        while (!exit) {
            displayCartView();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 5);
            switch (choice) {
                case 1:
                    showAllProductInCart();
                    break;
                case 2:
                    updateProductInCart();
                    break;
                case 3:
                    purchaseProduct();
                    break;
                case 4:
                    rateProduct();
                    break;
                case 5:
                    userLoggedInMenu();
                    break;
            }
        }
    }

    public void showAllProductInCart() {
        shoppingCart.viewCart(currentUser);
    }

    public void updateProductInCart() {
        if (!shoppingCart.isEmp(currentUser)) {
            showAllProductInCart();
            System.out.println("1. Update a Product");
            System.out.println("2. Delete a Product");
            int choice = MyUtils.inputInteger("Select Choice", 1, 2);
            if (choice == 1) {
                String toUpdateProduct = MyUtils.inputString("Enter Product name you want to update: ");
                int updateQuantity = MyUtils.inputInteger("Enter Quantity to update: ", 0, shoppingCart.getProductInCartQuantity(toUpdateProduct));
                shoppingCart.updateProductQuantity(toUpdateProduct, updateQuantity);
            }

            if (choice == 2) {
                String toDeleteProduct = MyUtils.inputString("Enter product id you want to delete: ");
                shoppingCart.removeProduct(toDeleteProduct);
            }
        } else {
            System.out.println("Cart is empty");
        }

    }

    public void purchaseProduct() {
        if (!shoppingCart.isEmp(currentUser)) {
            List<Product> l = new ArrayList<>();
            showAllProductInCart();
            do {
                String inputProductname = MyUtils.inputString("Enter Product name you want to purchase: ");
                l.add(shoppingCart.getProduct(inputProductname));
            } while (checkContinue() == true);

            for (Product product : l) {
                System.out.println(product);
            }

            if (checkConfirmInfo() == false) {
                System.out.println("Back to view Cart");
                cartView();
            } else {
                shoppingCart.purchaseProduct(l);
            }
        } else {
            System.out.println("Cart is empty");
        }

    }

    public void rateProduct() {
        purchasedList.readFromProductPurchasedList();
        do {
            purchasedList.viewPurchasedList(currentUser);
            String inputtedProductID = MyUtils.inputString("Enter Product ID you want to rate: ");
            int inputRating = MyUtils.inputInteger("Rating this product: ", 0, 5);
            productList.rateProduct(inputtedProductID, inputRating);
            purchasedList.remove(inputtedProductID);
        } while (checkContinue());
        purchasedList.writeProductToPurchasedList();
    }

    public boolean checkContinue() {
        String answer = MyUtils.inputString("Do you want to continue purchasing? Press 'y' to continue or 'n' to stop");
        if (answer.toLowerCase().equals("y")) {
            return true;
        } else if (answer.toLowerCase().equals("n")) {
            return false;
        }

        return false;
    }

    public boolean checkConfirmInfo() {
        String answer = MyUtils.inputString("Confirm your products before purchasing? Press 'y' to agreee or 'n' to disagree");
        return answer.toLowerCase().equals("y");
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

    public void showProduct() {
        System.out.println("==== Show Product in Shop ====");
        productList.readFromProductList();
        List<Product> product = productList.toList();
        for (Product prd : product) {
            if (prd.getShopowner().equals(currentOwner)) {
                System.out.println(prd);
            } else {
                System.out.println("There are no stores available");
            }
        }
    }

    public void addProductToShop() {
        productList.readFromProductList();
        System.out.println("==== Add Product to Shop ====");
        String productID = "";
        String productName = "";
        try {
            do {
                productID = MyUtils.inputString("Enter product ID: ");
            } while(!Validation.isValidProductId(productID));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        do {
            productName = MyUtils.inputString("Enter Product name: ");
        } while(!Validation.isValidProductName(productName));
        double price = (double) MyUtils.inputInteger("Enter product price:", 0, Integer.MAX_VALUE);
        int quantity = MyUtils.inputInteger("Enter product quantity:", 0, Integer.MAX_VALUE);
        double rating = 0.0;
        int soldQuantity = 0;

        Product product = new Product(productID, productName, quantity, price, soldQuantity, rating, currentOwner);
        productList.addProduct(product);
        productList.writeProductToList();
        System.out.println("Product added to the shop");
    }

    public void removeProductFromShop() {
        System.out.println("==== Remove Product from Shop ====");
        String productID = MyUtils.inputString("Enter product ID to remove: ");

        productList.readFromProductList();
        for (Product prd : productList.toList()) {
            if (prd.getProductID().equals(productID) && prd.getShopowner().equals(currentOwner)) {
                productList.removeProduct(productID);
                break;
            } else {
                System.out.println("Product not found in the shop");
            }
        }
        productList.writeProductToList();
    }

    public void updateProductInShop() {
        System.out.println("==== Update Product in Shop ====");
        String productID = MyUtils.inputString("Enter product ID to update: ");
        String productName = MyUtils.inputString("Enter new product name: ");
        double price = (double) MyUtils.inputInteger("Enter new product price:", 0, Integer.MAX_VALUE);
        int quantity = MyUtils.inputInteger("Enter new product quantity:", 0, Integer.MAX_VALUE);

        productList.readFromProductList();
        for (Product prd : productList.toList()) {
            if (prd.getProductID().equals(productID) && prd.getShopowner().equals(currentOwner)) {
                prd.setProductName(productName);
                prd.setPrice(price);
                prd.setQuantity(quantity);
                break;
            } else {
                System.out.println("Product not found in the shop.");
            }
        }
        productList.writeProductToList();
    }
}
