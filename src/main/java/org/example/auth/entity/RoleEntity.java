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
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

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
