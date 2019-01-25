package me.wordmaster.service;

import me.wordmaster.dao.BadgeMapper;
import me.wordmaster.dao.UserMapper;
import me.wordmaster.dao.UserWordMapper;
import me.wordmaster.model.AppUser;
import me.wordmaster.model.Badge;
import me.wordmaster.model.TopUser;
import me.wordmaster.security.JWTService;
import me.wordmaster.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserMapper userdao;

    @Autowired
    private BadgeMapper badgedao;

    @Autowired
    private UserWordMapper userworddao;

    @Autowired
    private JWTService jwtservice;

    public UserVO login(String username, String pass) {
        String hashedpass = hash(username+pass);
        AppUser user = userdao.userLogin(username, hashedpass);
        if (user != null) {
            List<Badge> badges = badgedao.listBadgesByUser(user.getId());
            String jwt = jwtservice.createToken(username);
            return new UserVO(user, badges, jwt);
        } else {
            return null;
        }
    }

    public AppUser getUserByName(String username) {
        return userdao.getUser(username);
    }

    public List<Integer> getLast7DayProgress(String username) {
        LocalDate localdate = LocalDate.now().minusDays(10);
        Date date = Date.valueOf(localdate);
        return userworddao.getLast7DayProgress(username, date);
    }

    public List<TopUser> getTopUser() {
        return userworddao.getTopUser();
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
