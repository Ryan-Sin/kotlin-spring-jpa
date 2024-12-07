package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction

@Entity
@Table(name = "member")
@SQLDelete(sql = "UPDATE member SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("deleted_at is NULL")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false, unique = true, length = 255)
    private var name: String,

    @Enumerated(EnumType.STRING)
    val gender: Gender,

    @Embedded
    val address: Address
): BaseEntity() {
    fun getName() = this.name
    fun setName(name: String) {
        this.name = name
    }
}