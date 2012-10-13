package com.bigsale.util;

import com.bigsale.service.security.AuthenticationProcessingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CipherUtil {
    public static final String ALGORITHM = "SHA-1";
    private Logger logger = LoggerFactory.getLogger(CipherUtil.class);

    public static String hashPassword(String password)
    {
        String passwordHash = "";
        try
        {
            MessageDigest sha1 = MessageDigest.getInstance(ALGORITHM);
            sha1.reset();
            sha1.update(password.getBytes());
            Base64 encoder = new Base64();
            passwordHash = new String(encoder.encode(sha1.digest()));
        }
        catch (NoSuchAlgorithmException e)
        {
            LoggerFactory.getLogger(AuthenticationProcessingFilter.class.getClass()).error(
                    "Failed to generate password hash: " + e.getMessage());
        }
        return passwordHash;
    }
}
