package com.noir.webthingauth.apicontrollers;

import com.noir.webthingauth.EncryptData;
import com.noir.webthingauth.domain.User;
import com.noir.webthingauth.filters.JWTConstants;
import com.noir.webthingauth.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<Map<String,String>> test() {
        Map<String, String> response = new HashMap<>();
        response.put("test", "TEST");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        User user = userService.validateUser(username, EncryptData.generate(password));
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        User user = userService.registerUser(username, EncryptData.generate(password));
        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWTConstants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + JWTConstants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("username", user.getUsername())
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
