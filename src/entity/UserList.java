/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtils;
import validate.Validation;

/**
 *
 * @author ASUS
 */
public class UserList extends ArrayList<User> {

    private static final long serialVersionUID = 1L;
    private static final String userFilePath = "ShoppingOnline\\src\\data\\userList.txt";

    public void writeToUserList() {
        try ( OutputStream os = new FileOutputStream(userFilePath);  ObjectOutputStream oos = new ObjectOutputStream(os)) {

            for (User user : this) {
                oos.writeObject(user);
            }
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromUserList() {
        File f = new File(userFilePath);
        if (!f.canRead()) {
            System.out.println("File cannot read");
        }
        try {
            FileInputStream is = new FileInputStream(userFilePath);
            ObjectInputStream ois = new ObjectInputStream(is);
            this.clear();
            while (true) {
                try {
                    User user = (User) ois.readObject();
                    this.add(user);
                } catch (EOFException e) {
                    break; // Break the loop when EOFException occurs
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("UserList size: " + this.size()); // Print the size for debugging
        } catch (EOFException eof) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAllUser() {

        this.readFromUserList();
        for (User user : this) {
            System.out.println(user);
        }
    }

    public void addAUser(User user) {
        this.add(user);
    }

    public User loginUser() {
        String username = MyUtils.inputString("Enter Username: ");
        String password = MyUtils.inputString("Enter Password: ");

        this.readFromUserList();

        for (User user : this) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void registerUser() {
        readFromUserList();
        String username = "";
        String password = "";
        String fullname = "";
        do {
            username = MyUtils.inputString("Enter your username: ");
        } while (!Validation.isValidUsername(username));

        do {
            password = MyUtils.inputString("Enter your password: ");
        } while (!Validation.isValidPassword(password));

        do {
            fullname = MyUtils.inputString("Enter your full name: ");
        } while (!Validation.isValidFullname(fullname));

        User registeredUser = new User(username, password, fullname);
        this.add(registeredUser);

        System.out.println("UserList size before writing: " + this.size()); // Print size before writing

        this.writeToUserList();

        System.out.println("UserList size after writing: " + this.size()); // Print size after writing
    }

}
