package com.example.backend.RegisterUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterUserRepository extends JpaRepository<RegisterUser,Long> {

    boolean existsByEmail(String email);
    RegisterUser findByEmail(String email);
}
