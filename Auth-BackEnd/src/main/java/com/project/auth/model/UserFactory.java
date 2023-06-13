package com.project.auth.model;

public class UserFactory {
    public User userSaveFactory(User user){
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(new PasswordEncryption().encrypting(user.getPassword()));

        return newUser;
    }
}
