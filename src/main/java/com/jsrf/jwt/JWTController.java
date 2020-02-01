package com.jsrf.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.security.Key;
import java.util.Date;

@RestController
public class JWTController {

    /**
     * User register with whose username and password
     *
     * @return Success message
     * @throws ServletException
     */
    @RequestMapping(value = "/register")
    public String register() throws ServletException {
        // Check if username and password is null
//        List<Role> roles = new ArrayList<Role>();
//        roles.add(Role.MEMBER);
        return "Register Success!";
    }

    /**
     * Check user`s login info, then create a jwt token returned to front end
     *
     * @return jwt token
     * @throws ServletException
     */
    @RequestMapping(value = "/login")
    public String login() throws ServletException {
        // Create Twt token
        ReqPerson reqPerson = new ReqPerson();
        reqPerson.setUserName("jsrf");
        reqPerson.setPassword("123456");
        //生成签名密钥，基于HMAC-SHA算法
        Key key = Keys.hmacShaKeyFor("secretkey2313131414131212gsdgsgsdgwegwe".getBytes());
        String jwtToken = Jwts.builder().setSubject(reqPerson.getUserName())
                .claim("roles", "member").setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
//                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();

        return jwtToken;
    }
}