/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enums.EUserRole;

/**
 *
 * @author ADMIN
 */
public class User {
    private String username;
    private String password; 
    private EUserRole role;

    public User(String username, String password, EUserRole role) {
        this(username,password);
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
    
}

