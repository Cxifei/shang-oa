package com.rimi.user.uitls;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 *
 * @author szf
 */
public class BPwdEncoderUtils {

    private static final PasswordEncoder ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder
     *
     * @param password
     * @return
     */
    public static String getPassword(String password) {
        return ENCODER.encode(password);
    }

    /**
     * @param rawPassword     原始密码
     * @param encodedPassword 加密后的密码
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return ENCODER.matches(rawPassword, encodedPassword);
    }
}
