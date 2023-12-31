package com.project.auth.model;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {
    public String encrypting(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkEncryptedPassword(String inputPassword, String dataPassword){
        return BCrypt.checkpw(inputPassword, dataPassword);
    }
}
