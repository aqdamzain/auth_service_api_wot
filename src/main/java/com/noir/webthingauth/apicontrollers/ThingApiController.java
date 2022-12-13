package com.noir.webthingauth.apicontrollers;

import com.noir.webthingauth.domain.Gateway;
import com.noir.webthingauth.domain.PGateway;
import com.noir.webthingauth.filters.JWTConstants;
import com.noir.webthingauth.service.thing.GatewayService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/things")
public class ThingApiController {

    @Autowired
    GatewayService gatewayService;

    @GetMapping
    public ResponseEntity<Map<String, String>> test(){
        return new ResponseEntity<Map<String, String>>(generateJWTToken("04 2C 38 7A F8 51 80"), HttpStatus.OK);
    }

    @GetMapping("/gateways")
    public ResponseEntity<List<PGateway>> getAllGateways(){
        return new ResponseEntity<>(gatewayService.getAllGateways(), HttpStatus.OK);
    }

    @PostMapping("/gateways/rfid")
    public ResponseEntity<Gateway> getAllGateways(@RequestBody Map<String, Object> requestBody){
        String rfid = (String) requestBody.get("rfid");
        return new ResponseEntity<>(gatewayService.getGateway(rfid), HttpStatus.OK);
    }

    private Map<String, String> generateJWTToken(String rfid) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, JWTConstants.API_CONTROL_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + JWTConstants.TOKEN_VALIDITY))
                .claim("rfid", rfid) //"04 2C 38 7A F8 51 80"
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
}
