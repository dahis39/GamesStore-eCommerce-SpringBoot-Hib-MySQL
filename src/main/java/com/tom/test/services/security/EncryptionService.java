package com.tom.test.services.security;

/**
 * Created by tom on 8/10/2016.
 */
public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
