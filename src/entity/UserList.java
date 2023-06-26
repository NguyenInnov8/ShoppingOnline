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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class UserList extends User {

    private static final String userFilePath = "src\\data\\data.txt";
    private List<User> userList = new ArrayList<>();

    public void writeToUserList() throws IOException {
        File f = new File(userFilePath);
        if (!f.exists()) {
            System.out.println("File does not exist");
            return;
        }
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(userFilePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for (User user : userList) {
                objectOut.writeObject(user);
            }
            System.out.println("Write Successfully to User List");
            fileOut.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void readFromUserList() {
    try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(userFilePath))) {
        while (true) {
            try {
                Object obj = objectIn.readObject();
                if (obj == null) {
                    break;
                } else if (obj instanceof User) {
                    User userReaded = (User) obj;
                    this.addAUser(userReaded);
                }
            } catch (EOFException ex) {
                // End of file reached, exit the loop
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } catch (FileNotFoundException ex) {
        System.out.println("File does not exist");
    } catch (IOException ex) {
        Logger.getLogger(UserList.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    public void addAUser(User user) {
        userList.add(user);
    }

    public void showAllUser() {
        for (User user : userList) {
            System.out.println(user);
        }
    }
    
   public User loginAccount() {
        String username = MyUtils.inputString("Enter Username: ");
        String password = MyUtils.inputString("Enter Password: ");

        // Read the user list only once, outside the loop
        readFromUserList();

        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println("Login Successfully");
                return user;
            }
        }
    return null;
}

}
