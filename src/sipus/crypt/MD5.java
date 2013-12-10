/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sipus.crypt;

/**
 *
 * @author Administrator
 */
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 {

    public static String getMD5(String plain) {
        String cipher = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] data = plain.getBytes();
            messageDigest.update(data, 0, data.length);
            BigInteger i = new BigInteger(1, messageDigest.digest());
            cipher = String.format("%1$032X", i);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cipher;
    }
}