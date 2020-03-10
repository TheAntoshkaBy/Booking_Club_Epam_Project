package by.epam.booking.defence;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

    private static Logger logger = LogManager.getLogger();
    private static String DIGEST = "MD2";

    public static String encrypt(String password) {
        String encryptPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(DIGEST);
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, bytes);
            encryptPassword = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Encrypt error ", e);
        }
        return encryptPassword;
    }
}
