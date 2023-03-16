package com.example.mockreskill.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Date;

@Audited
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@EnableJpaAuditing
public abstract class AuditingEntity  {
    @JsonIgnore
    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @JsonIgnore
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    @JsonIgnore
    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;
    public AuditingEntity() {
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(LocalDateTime now) {
        return this.updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @JsonIgnore
    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonIgnore
    public void setUpdatedBy(final String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
