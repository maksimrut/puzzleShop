package com.rutkouski.puzzleshop.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Maksim Rutkouski
 *
 * The type Password encryptor.
 */
public class PasswordEncryptor {
    private PasswordEncryptor() {
    }

    /**
     * Encrypt string password by using apache codec
     *
     * @param password to encrypt
     * @return the encrypted string
     */
    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }
}
