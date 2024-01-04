package com.bej.authentication.security;

import com.bej.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenImpl implements SecurityTokenGenerator{

    @Override
    public String createToken(User user) {
        Map<String,Object> claim=new HashMap<>();
        claim.put("email",user.getEmail()); //10
        return generateToken(claim, user.getEmail());
    }

    public String generateToken(Map<String,Object> claims,String subject) {
        System.out.println("generate token"+claims+"subject "+subject);
       String jwtToken = Jwts.builder()
               .setIssuer("Authorization")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"confidential")
                .compact();
        return jwtToken;
    }
}
