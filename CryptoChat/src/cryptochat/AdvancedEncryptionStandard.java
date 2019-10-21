/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptochat;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Nemanja Lazarević
 */
public class AdvancedEncryptionStandard {

    private byte[] key;

    public AdvancedEncryptionStandard(byte[] key) {
        this.key = key;
    }

    public AdvancedEncryptionStandard() {
    }
    

    public byte[] encrypt(byte[] plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }

    public static void main(String[] args) throws Exception {
        byte[] encryptionKey = "1234567890123456".getBytes();
        byte[] plainText = "Hello world!".getBytes();
        AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(encryptionKey);
        byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
        byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);

        System.out.println(new String(plainText));
        System.out.println(new String(cipherText));
        System.out.println(new String(decryptedCipherText));

        String encrypted = new BASE64Encoder().encodeBuffer(cipherText);
        System.out.println(new String(encrypted));

        byte[] valueDecoded = new BASE64Decoder().decodeBuffer("CdwP6KNyEX4jUv5PcnLZlCAHzEgiEXrDaA+fN1LXE1gfYAD49PcfvCQ/U3zbKn0v");
        System.out.println("Decoded value is " + new String(valueDecoded));

        byte[] decryptedCipherText1 = advancedEncryptionStandard.decrypt(valueDecoded);
        System.out.println("Desifrovano: " + new String(decryptedCipherText1));

    }
}
