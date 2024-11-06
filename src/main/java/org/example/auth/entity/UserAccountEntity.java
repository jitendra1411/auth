package org.example.auth.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.auth.util.ThreadLocalUtils;

import java.time.Instant;

/**
 * @author Jitendra (Jeetu)
 */


@Data
@Entity
@Table(name = "user_account")
public class UserAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_verified")
    private Boolean phoneVerified;

    @Column(name = "status")
    private String status;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;

    @Column(name="updated_by")
    private Long updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdOn = this.lastUpdatedOn = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedBy = ThreadLocalUtils.getThreadLocalUser();
        this.lastUpdatedOn = Instant.now();
    }
}
