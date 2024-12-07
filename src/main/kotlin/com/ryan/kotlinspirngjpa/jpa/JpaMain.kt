package com.ryan.kotlinspirngjpa.jpa

import com.ryan.kotlinspirngjpa.jpa.entity.Address
import com.ryan.kotlinspirngjpa.jpa.entity.Gender
import com.ryan.kotlinspirngjpa.jpa.entity.MemberEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

fun main() {
    val emf = Persistence.createEntityManagerFactory("ryan")
    val em = emf.createEntityManager()

    createMember(em)
    val memberEntity = findMemberByName(em)
    updateMember(memberEntity, em)
    removeMember(memberEntity, em)

    em.close()
    emf.close()
}

fun createMember(em: EntityManager) {
    val tx = em.transaction
    tx.begin()
    try {
        val address = Address(
            address = "서울특별시 강남구 테헤란로 123",
            roadAddress = "서울특별시 강남구 테헤란로",
            detailAddress = "101동 201호",
            postCode = "12345",
            doorCode = "A1234"
        )
        val member = MemberEntity(name = "rayn", gender = Gender.MAN, address = address)
        em.persist(member)
        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    }
}

fun findMemberByName(em: EntityManager): MemberEntity {
    val memberEntity = em.find(MemberEntity::class.java, 1L)
    println("Member Id = ${memberEntity.id}")
    println("Member Name = ${memberEntity.getName()}")
    println("Member CreatedAt = ${memberEntity.createdAt}")
    println("Member UpdatedAt = ${memberEntity.updatedAt}")
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