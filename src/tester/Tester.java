/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tester;

import entity.User;
import entity.UserList;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Tester {
    public static void main(String[] args){
            //User test = new User("nguyen1223", "Lenguyen1002@@@", "Nguyen Le Nguyen Ha");
            //User test2 = new User("baoha123", "Baoha123@", "Ha Minh Quoc Bao");
            UserList userList = new UserList();
//            if(userList.loginUser() != null) {
//                System.out.println("Login Successfully");
//            } else {
//                System.out.println("Login fail");
//            }
            userList.registerUser();
            userList.showAllUser();
    }
}
