package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.*
import java.time.Instant

@MappedSuperclass
abstract class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now()

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
        protected set

    @Column(name = "deleted_at")
    val deletedAt: Instant? = null

    @PreUpdate
    fun onUpdate() {
        this.updatedAt = Instant.now()
    }
}