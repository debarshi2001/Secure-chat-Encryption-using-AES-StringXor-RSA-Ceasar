/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat_app;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author Asus
 */
public class AESUtils {
     private static final String SALT = "D7mS!z8@pG5^XhT2&Jf3#Ue4*Ri6Nc0)Ld+qA~YQ1{B}9|HwV>Zr,<kK/W_;Mb:F?jgnPo_El2Ot3Ch^xF6=8gLp$Us#Nz5,Di*w7Qv4y2H^m{1k4}(H!5@J3D?_0tQ+p8y=fX&c^Ws$1U2Z?Gf(j6+K;Ld-MBiL)F:Wwz3>oN|2";

        public static String encrypt(String strToEncrypt, String SECRET_KEY) {
                try {
                        // Create default byte array
                        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                        IvParameterSpec ivspec = new IvParameterSpec(iv);

                        // Create SecretKeyFactory object
                        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
                        SecretKey tmp = factory.generateSecret(spec);
                        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
                        // Return encrypted string
                        return Base64.getEncoder()
                                        .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                        System.out.println("Error while encrypting: " + e.toString());
                }
                return null;
        }

        // This method use to decrypt to string
        public static String decrypt(String strToDecrypt, String SECRET_KEY) {
                try {

                        // Default byte array
                        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0,
                                      0, 0, 0, 0, 0, 0, 0, 0 };
                        IvParameterSpec ivspec = new IvParameterSpec(iv);
                        // Create SecretKeyFactory Object
                        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
                        SecretKey tmp = factory.generateSecret(spec);
                        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
                        // Return decrypted string
                        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                        System.out.println("Error while decrypting: " + e.toString());
                }
                return null;
        }
}
