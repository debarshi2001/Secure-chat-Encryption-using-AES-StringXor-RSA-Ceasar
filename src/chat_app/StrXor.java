/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;


public class StrXor {

    // Encrypts the data using XOR with the given key
    public static String encrypt(String data, String key) {
        if (data == null || key == null || data.isEmpty() || key.isEmpty()) {
            throw new IllegalArgumentException("Data and key cannot be null or empty");
        }
        return xor(data, key);
    }

    // Decrypts the data using XOR with the given key
    public static String decrypt(String encryptedData, String key) {
        return xor(encryptedData, key); // XOR encryption and decryption are symmetric
    }

    // Performs XOR operation on data with the given key
    private static String xor(String data, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char encryptedChar = (char) (data.charAt(i) ^ key.charAt(i % key.length()));
            result.append(encryptedChar);
        }
        return result.toString();
    }
}