/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;

/**
 *
 * @author Asus
 */
public class CaesarCipher {
     public static String encrypt(String text, int s) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                char encryptedChar = (char) (((ch - 'A' + s) % 26) + 'A');
                result.append(encryptedChar);
            } else if (Character.isLowerCase(ch)) {
                char encryptedChar = (char) (((ch - 'a' + s) % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(ch); // keep other characters unchanged
            }
        }
        
        return result.toString();
    }
    
    // Decrypts text using a shift of s
    public static String decrypt(String text, int s) {
        return encrypt(text, 26 - s); // Decryption is just encryption with reverse shift
    }

}
