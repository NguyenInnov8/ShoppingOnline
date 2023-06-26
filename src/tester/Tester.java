/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tester;

import entity.User;
import entity.UserList;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        User test = new User("Nguyen", "12930129", "Nguyen Le Nguyen");
        UserList userList = new UserList();
        userList.addAUser(test);
        userList.writeToUserList();
        userList.readFromUserList();
        userList.showAllUser();
    }
}
