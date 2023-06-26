package com.example.ticketbusreservation.service.impl;

import com.example.ticketbusreservation.dtoRequests.UserRequestDto;
import com.example.ticketbusreservation.exceptions.PasswordDoNotMatchException;
import com.example.ticketbusreservation.exceptions.UserAlreadyExistsException;
import com.example.ticketbusreservation.models.User;
import com.example.ticketbusreservation.repository.UserRepository;
import com.example.ticketbusreservation.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Boolean passwordMatches(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        this.userRepository.deleteById(user.getId());
    }

    @Override
    public Optional<User> register(UserRequestDto userRequestDto) {
        User user = this.userRepository.findByEmail(userRequestDto.getEmail());
        if(user != null){
            throw new UserAlreadyExistsException();
        }
        if(!userRequestDto.getPassword().equals(userRequestDto.getConfirmPassword())){
            throw new PasswordDoNotMatchException();
        }
        return Optional.of(this.userRepository.save(new User(
                userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getEmail(),
                passwordEncoder.encode(userRequestDto.getPassword()))));
    }

    @Override
    public Optional<User> details(String email) {
        return Optional.of(this.userRepository.findByEmail(email));
    }
}
