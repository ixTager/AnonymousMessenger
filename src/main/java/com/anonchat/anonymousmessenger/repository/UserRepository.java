package com.anonchat.anonymousmessenger.repository;

import com.anonchat.anonymousmessenger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmailIgnoreCase(String email);
}
