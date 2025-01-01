package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.*

@Entity
@Table(name = "member_profile")
class MemberProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "age", nullable = false)
    val age: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    val gender: Gender,

    @Column(name = "biography", nullable = true)
    val biography: String? = null
) : BaseEntity()