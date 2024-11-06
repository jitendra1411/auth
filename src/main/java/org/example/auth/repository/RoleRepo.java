package org.example.auth.repository;

import org.example.auth.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jitendra (Jeetu)
 */


@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByNameAndStatus(String name, String status);
}
