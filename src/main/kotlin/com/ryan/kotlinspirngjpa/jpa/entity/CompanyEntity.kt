package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "company")
class CompanyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: CompanyStatus = CompanyStatus.ACTIVE,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "business_number", nullable = false)
    val businessNumber: String,

    @Column(name = "ceo_name", nullable = false)
    val ceoName: String,

    @Column(name = "founded_date", nullable = false)
    val foundedDate: Instant,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "phone_number", nullable = false)
    val phoneNumber: String,

    @Embedded
    val address: Address,

    @ManyToMany
    @JoinTable(
        name = "company_category",
        joinColumns = [JoinColumn(name = "company_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    val categories: MutableSet<CategoryEntity> = mutableSetOf()

) : BaseEntity()