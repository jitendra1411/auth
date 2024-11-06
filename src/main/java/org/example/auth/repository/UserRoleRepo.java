package org.example.auth.repository;

import org.example.auth.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jitendra (Jeetu)
 */


@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByUserId(Long userId);
}
