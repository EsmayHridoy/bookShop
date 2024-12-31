package com.assignment.bookShop.Utils;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HashPassword {

    public static String hashPassword(String password) {
        try {
            byte[] hashedBytes = hash(password);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage(), e);
        }
    }

    public static boolean verifyPassword(String password, String storedHash) {
        try {
            String actualHash = hashPassword(password);
            return actualHash.equals(storedHash);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying password: " + e.getMessage(), e);
        }
    }

    private static byte[] hash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes());
    }
}
