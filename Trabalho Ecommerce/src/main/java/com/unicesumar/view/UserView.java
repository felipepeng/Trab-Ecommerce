package com.unicesumar.view;

import com.unicesumar.entities.User;

public class UserView {

    public void showUser(User user) {
        System.out.println("Nome: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("|------------------------------------|");
    }

}
