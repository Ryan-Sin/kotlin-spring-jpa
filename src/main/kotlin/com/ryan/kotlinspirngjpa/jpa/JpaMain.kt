package com.ryan.kotlinspirngjpa.jpa

import com.ryan.kotlinspirngjpa.jpa.entity.*
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import java.time.Instant

fun main() {
    val emf = Persistence.createEntityManagerFactory("ryan")
    val em = emf.createEntityManager()

    createEntity(em)
    val memberEntity = findMemberByName(em)
//    setDetach(memberEntity, em)
//    updateMember(memberEntity, em)
//    removeMember(memberEntity, em)

    em.close()
    emf.close()
}

fun createEntity(em: EntityManager) {
    val tx = em.transaction
    tx.begin()
    try {
        for (i in 1..10) {
            val address = Address(
                address = "서울특별시 강남구 테헤란로 123",
                roadAddress = "서울특별시 강남구 테헤란로",
                detailAddress = "101동 201호",
                postCode = "12345",
                doorCode = "A1234"
            )

            val category1 = CategoryEntity(name = "IT", parent = null)
            val category2 = CategoryEntity(name = "Finance", parent = null)

            val subCategory1 = CategoryEntity(name = "Programming", parent = category1)
            val subCategory2 = CategoryEntity(name = "Investing", parent = category2)

            em.persist(category1)
            em.persist(category2)
            em.persist(subCategory1)
            em.persist(subCategory2)

            val company = CompanyEntity(
                status = CompanyStatus.ACTIVE,
                name = "예시 기업 $i",
                businessNumber = "123456789$i",
                ceoName = "홍길동 $i",
                foundedDate = Instant.now(),
                email = "contact$i@example.com",
                phoneNumber = "123-456-789$i",
                address = address,
                categories = mutableSetOf(category1, category2, subCategory1, subCategory2)
            )
            em.persist(company)

            val profile = MemberProfileEntity(age = 30, gender = Gender.MAN)
            em.persist(profile)

            val member = MemberEntity(
                email = "rayn$i@example.com",
                password = "password123$i",
                name = "rayn $i",
                profile = profile,
                company = company
            )
            em.persist(member)
        }
        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    }
}

fun findMemberByName(em: EntityManager): MemberEntity {
    em.clear()
    val memberEntity = em.find(MemberEntity::class.java, 1L)
    println("=======Member=======")
    println("Member id = ${memberEntity.id}")
    println("Member name = ${memberEntity.getName()}")
    println("Member email = ${memberEntity.email}")
    println("Member password = ${memberEntity.password}")

    println("=======Profile=======")
    println("Member Profile id = ${memberEntity.profile.id}")
    println("Member Profile age = ${memberEntity.profile.age}")
    println("Member Profile gender = ${memberEntity.profile.gender}")
    println("Member Profile biography = ${memberEntity.profile.biography}")

    println("=======Company=======")
    println("Member Company id = ${memberEntity.company.id}")
    println("Member Company status = ${memberEntity.company.status}")
    println("Member Company name = ${memberEntity.company.name}")
    println("Member Company businessNumber = ${memberEntity.company.businessNumber}")
    println("Member Company ceoName = ${memberEntity.company.ceoName}")
    println("Member Company foundedDate = ${memberEntity.company.foundedDate}")
    println("Member Company email = ${memberEntity.company.email}")
    println("Member Company phoneNumber = ${memberEntity.company.phoneNumber}")
    println("Member Company address = ${memberEntity.company.address}")

    return memberEntity
}

fun updateMember(member: MemberEntity, em: EntityManager) {
    val tx = em.transaction
    tx.begin()
    try {
        member.setName("HelloB")
        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    }
}

fun removeMember(member: MemberEntity, em: EntityManager) {
    val tx = em.transaction
    tx.begin()
    try {
        em.remove(member)
        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    }
}

fun setDetach(member: MemberEntity, em: EntityManager) {
    em.detach(member)
}