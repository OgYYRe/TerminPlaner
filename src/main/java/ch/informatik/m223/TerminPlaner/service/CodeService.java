package ch.informatik.m223.TerminPlaner.service;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CodeService {
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;
    private final SecureRandom random = new SecureRandom();

    // öffentlichen Code
    public String generatePublicCode() {
        return generateRandomCode();
    }

    // privaten Code
    public String generatePrivateCode() {
        return generateRandomCode();
    }

    // Generierung für einen Code
    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
