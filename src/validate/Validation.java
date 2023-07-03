package validate;

import entity.Product;
import entity.ProductList;
import entity.User;
import entity.UserList;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final UserList list = new UserList();
    private static final ProductList prdList = new ProductList();

    public static boolean isValidUsername(String username) {
        System.out.println("Username Error:");
        int minLenUsername = 5;
        int maxLenUsername = 20;

        if (username == null || username.length() == 0) {
            System.out.println("Username cannot be empty!");
            return false;
        }

        if (username.length() < minLenUsername || username.length() > maxLenUsername) {
            System.out.println("Username cannot be smaller than " + minLenUsername + " or larger than " + maxLenUsername + " Characters");
            return false;
        }

        if (isUppercaseUsername(username)) {
            System.out.println("Username cannot contain uppercase letters");
            return false;
        }

        if (isDuplicateUsername(username, list)) {
            System.out.println("This Username has existed. Please choose another username");
            return false;
        }

        char[] usernameChar = username.trim().toCharArray();
        int usernameCharLen = usernameChar.length;
        int currentPosition = 0;

        if (isSupportedSymbolsUsername(usernameChar[0]) || isSupportedSymbolsUsername(usernameChar[usernameCharLen - 1])) {
            System.out.println("Cannot use these symbols at the beginning or the end of the username: '_', '.', '-'");
            return false;
        }

        for (char c : usernameChar) {
            if (!Character.isLetterOrDigit(c)) {
                if (!isSupportedSymbolsUsername(c)) {
                    System.out.println("Cannot use any symbols except '_', '.', '-'");
                    return false;
                }

                if (isSupportedSymbolsUsername(c) && isSupportedSymbolsUsername(usernameChar[currentPosition + 1])) {
                    System.out.println("These symbols ('_', '.', '-') cannot be consecutively");
                    return false;
                }

                if (Character.isWhitespace(c)) {
                    System.out.println("Username cannot contain whitespace");
                    return false;
                }
            }
            currentPosition++;
        }

        return true;
    }

    private static boolean isSupportedSymbolsUsername(char c) {
        char[] supportedCharacters = new char[]{'_', '.', '-'};
        for (char supportedCharacter : supportedCharacters) {
            if (supportedCharacter == c)
                return true;
        }
        return false;
    }

    private static boolean isUppercaseUsername(String username) {
        char[] usernameChar = username.trim().toCharArray();
        for (char c : usernameChar) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    private static boolean isDuplicateUsername(String username, UserList list) {
        list.readFromUserList();
        for (User user : list) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        System.out.println("Password Error:");
        char[] passwordArray = password.toCharArray();
        int countDigit = 0;
        int countLowerLatinCharacter = 0;
        int countUpperLatinCharacter = 0;
        int countSpecialSymbol = 0;
        int countSpace = 0;
        int countInvalid = 0;
        for (int i = 0; i < passwordArray.length; i++) {
            if (Character.isDigit(passwordArray[i]))
                countDigit++;
            else if (Character.isLowerCase(passwordArray[i]))
                countLowerLatinCharacter++;
            else if (Character.isUpperCase(passwordArray[i]))
                countUpperLatinCharacter++;
            else if (isSupportedSpecialSymbolsPassword(passwordArray[i]) == true)
                countSpecialSymbol++;
            else if (Character.isWhitespace(passwordArray[i])) {
                countSpace++;
            }
        }

        if (password == null || password.length() == 0) {
            System.out.println("Password must not be empty");
        }

        if (countDigit == 0) {
            System.out.println("Password must contain at least one digit [0-9].");
            countInvalid++;
        }

        if (countLowerLatinCharacter == 0) {
            System.out.println("Password must contain at least one lowercase Latin character [a-z].");
            countInvalid++;
        }

        if (countUpperLatinCharacter == 0) {
            System.out.println("Password must contain at least one uppercase Latin character [A-Z].");
            countInvalid++;
        }

        if (countSpecialSymbol == 0) {
            System.out.println("Password must contain at least one special character like ! @ # & ");
            countInvalid++;
        }

        if (password.length() < 5 || password.length() > 20) {
            System.out.println("Password must contain a length of at least 8 characters and a maximum of 20 characters");
            countInvalid++;
        }

        if (countSpace != 0) {
            System.out.println("Password must not contain whitespace");
            countInvalid++;
        }

        return countInvalid == 0;
    }

    private static boolean isSupportedSpecialSymbolsPassword(char c) {
        char[] specialCharacters = {'!', '@', '&', '#'};
        for (char specialCharacter : specialCharacters) {
            if (specialCharacter == c)
                return true;
        }
        return false;
    }

    public static boolean isValidFullname(String fullname) {
        char[] fullnameChar = fullname.trim().toCharArray();

        if (Character.isLowerCase(fullnameChar[0])) {
            System.out.println("First Letter of each words of fullname must be uppercase");
            return false;
        }

        if (Character.isWhitespace(fullnameChar[0])) {
            System.out.println("Wrong Format! You cannot enter whitespace characters at the beginning of fullname");
            return false;
        }

        for (int i = 1; i < fullnameChar.length; i++) {
            if (Character.isLowerCase(fullnameChar[i]) && Character.isWhitespace(fullnameChar[i - 1])) {
                System.out.println("First Letter of each words of fullname must be uppercase");
                return false;
            }

            if (Character.isWhitespace(fullnameChar[i]) && Character.isWhitespace(fullnameChar[i - 1]) && Character.isLetter(fullnameChar[i + 1])) {
                System.out.println("Your fullname seems like in wrong format(More than one space between 2 words).");
                return false;
            }

        }
        return true;
    }

    // Validation function for Product

    public static boolean isValidProductId(String prdID) throws IOException, ClassNotFoundException {
        String validProductIDPattern = "prd\\d{6}";
        Pattern regex = Pattern.compile(validProductIDPattern);
        Matcher matcher = regex.matcher(prdID);

        if (!matcher.matches()) {
            System.out.println("Please enter correct product ID Format (prdxxxxxx, with x is natural number)");
            return false;
        }

        prdList.readFromProductList();

        for (Product product : prdList.toList()) {
            if (product.getProductID().equals(prdID)) {
                System.out.println("This Product ID already exists, please choose another product ID");
                return false;
            }
        }
        return true;
    }

    public static boolean isValidProductName(String prdName) {
        char[] prdNameChar = prdName.trim().toCharArray();

        for (int i = 0; i < prdNameChar.length; i++) {
            if (Character.isWhitespace(prdNameChar[i]) && Character.isWhitespace(prdNameChar[i + 1])) {
                System.out.println("Your product name seems like in wrong format(More than one space between 2 words).");
                return false;
            }

        }
        return true;
    }

    public static boolean isValidProductQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity cannot be under 0");
            return false;
        }
        return true;
    }

    public static boolean isValidProductPrice(double price) {
        if (price < 0) {
            System.out.println("Price cannot be under 0");
            return false;
        }
        return true;
    }

    public static boolean isValidProductRating(double rating) {
        if (rating < 0 || rating > 5) {
            System.out.println("Rating must be between 0 and 5");
            return false;
        }
        return true;
    }

    public static boolean isValidProductSoldQuantity(int soldQuantity) {
        if (soldQuantity != 0) {
            System.out.println("Sold Quantity should be initialized to 0");
            return false;
        }
        return true;
    }
}
