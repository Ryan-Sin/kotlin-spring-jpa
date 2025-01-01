package com.ryan.kotlinspirngjpa.jpa.entity

import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    val parent: CategoryEntity?,

    @OneToMany(mappedBy = "parent")
    val childCategorise: List<CategoryEntity> = listOf(),

    @ManyToMany(mappedBy = "categories")
    val companies: MutableSet<CompanyEntity> = mutableSetOf()

) : BaseEntity()