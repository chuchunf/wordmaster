package me.wordmaster.service;

import me.wordmaster.dao.UserMapper;
import me.wordmaster.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserMapper userdao;

    public AppUser login(String username, String pass) {
        String hashedpass = hash(username+pass);
        return userdao.userLogin(username, hashedpass);
    }

    public AppUser getUserByName(String username) {
        return userdao.getUser(username);
    }

    private String hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("not able to load algorithm SHA-256: "+e);
            throw new IllegalArgumentException("encryption error", e);
        }
    }
}
