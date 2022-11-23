package com.mahoa;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class DataDecryption {
    public static void main(String[] args) {
        try {
            // Đọc file chứa private key
            FileInputStream fis = new FileInputStream("D:/privateKey.rsa");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            fis.close();

            // Tạo private key
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey priKey = factory.generatePrivate(spec);

            // Giải mã dữ liệu
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, priKey);
            byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(
                    "YoO99hEZ1fKd7Ft72UWd3sX9/f95AjA5QOIVHhgEi58FPIDwnnVmvW/gANO/Pgk9s+Z6oYE2J3X7vkSJF/n5HpuNFKqquLcoM869ypr++WiISMG4pN0pKsgWBaBUObLBes3Rsl1GpD1QVn1Gbp1riFQ9Z8EBypnefcWsJcVnyzk="));
            System.out.println("Dữ liệu sau khi giải mã: " + new String(decryptOut));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
