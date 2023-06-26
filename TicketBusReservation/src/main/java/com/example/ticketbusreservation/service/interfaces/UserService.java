package com.example.ticketbusreservation.service.interfaces;

import com.example.ticketbusreservation.dtoRequests.UserRequestDto;
import com.example.ticketbusreservation.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByEmail(String email);

    Boolean passwordMatches(User user, String password);
    User save(User user);

    void deleteUserByEmail(String email);

    Optional<User> register(UserRequestDto userRequestDto);

    Optional<User> details(String email);

}
