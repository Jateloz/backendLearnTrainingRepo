package com.example.backend.RegisterUser;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserService {
    private final RegisterUserRepository repository;

    public RegisterUserService(RegisterUserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(String name,String email,Long phoneNumber,String password){
        if (repository.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }else {
            RegisterUser user = new RegisterUser();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phoneNumber);
            user.setPassword(password);

            repository.save(user);
        }
    }
    public RegisterUser validateLogins(String email,String password){
        RegisterUser user = repository.findByEmail(email);
        if (user == null){
            throw new IllegalArgumentException("Email does not exist");
        }else {

            return user;
        }
    }
}
