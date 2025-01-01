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

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "name", nullable = false)
    private var name: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_profile_id", referencedColumnName = "id")
    val profile: MemberProfileEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    val company: CompanyEntity

) : BaseEntity() {
    fun getName() = this.name
    fun setName(name: String) {
        this.name = name
    }
}