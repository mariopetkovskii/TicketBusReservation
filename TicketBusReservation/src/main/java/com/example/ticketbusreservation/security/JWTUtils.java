package com.example.ticketbusreservation.security;

import com.auth0.jwt.JWT;
import com.example.ticketbusreservation.exceptions.PasswordDoNotMatchException;
import com.example.ticketbusreservation.exceptions.UserNotEnabledException;
import com.example.ticketbusreservation.models.User;
import com.example.ticketbusreservation.service.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.example.ticketbusreservation.security.SecurityConstants.*;

@Component
public class JWTUtils {

    private final UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    public JWTUtils(UserService userService) {
        this.userService = userService;
    }

    public List<GrantedAuthority> addAuthoritiesFromRoles(User user, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getIsEnabled()) {
            if (!userService.passwordMatches(user, password)) {
                throw new PasswordDoNotMatchException();
            }
            authorities.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
        } else {
            throw new UserNotEnabledException();
        }

        return authorities;
    }

    public String createAndAppendToken(UserDetails userDetails, HttpServletResponse res) throws IOException {
        User user = userService.findByEmail(userDetails.getUsername());
        this.userService.save(user);
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("id", user.getId())
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withArrayClaim(SecurityConstants.CLAIM_AUTHORITY, (userDetails).getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
//        String jsonResponse = this.objectMapper.writeValueAsString(map);
//        res.setContentType("application/json");
//        res.getWriter().append(jsonResponse);
        res.getWriter().append(token);

        return token;
    }

    public String createAndAppendToken(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        UserDetails userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        return createAndAppendToken(userDetails, res);
    }
}