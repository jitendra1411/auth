package org.example.auth.repository;

import org.example.auth.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccountEntity, UUID> {
    UserAccountEntity findByPhone(String phone);
    UserAccountEntity findByEmail(String email);
    UserAccountEntity findByUsername(String username);
}
