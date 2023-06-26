package com.example.ticketbusreservation.jwtdecored;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import static com.example.ticketbusreservation.security.SecurityConstants.SECRET;

public class JwtDecoder {
    public static String decodeJwtToGetEmail(String jwtToken) {
        String token = jwtToken.substring(7); // remove "Bearer " prefix
        String email = null;


        try {
            email = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
            ;
        } catch (JWTVerificationException e) {
            // handle verification exception
        }
        return email;
    }
}
