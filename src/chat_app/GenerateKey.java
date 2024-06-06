/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;

import java.security.SecureRandom;

/**
 *
 * @author Asus
 */
public class GenerateKey {

    public static String generateRandomKey(int length) {
        final String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder key = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            key.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
        }

        return key.toString();
    }
    public static int generateNumber(){
        SecureRandom random = new SecureRandom();
        return random.nextInt(20);
    }

}
