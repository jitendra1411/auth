package org.example.auth.dto.user;

import lombok.Data;

import java.time.Instant;

/**
 * @author Jitendra (Jeetu)
 */

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String phone;
    private boolean phoneVerified;
    private String email;
    private boolean emailVerified;
    private String status;
    private String role;
    private Instant createdOn;
    private Instant lastUpdatedOn;
}
