package com.example.OTP.Entrance.Backend.Repositories;

import com.example.OTP.Entrance.Backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByIdNumber(Long idNumber);
    boolean existsByRegNumber(String regNumber);
    Optional<User> findByRegNumber(String regNumber);
    Optional<User> findByIdNumber(Long idNumber);
}
