package com.ryan.kotlinspirngjpa.jpa

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
        val member = MemberEntity(name = "HelloA")
        em.persist(member)
        tx.commit()
    } catch (e: Exception) {
        tx.rollback()
    }
}

fun findMemberByName(em: EntityManager): MemberEntity {
    val memberEntity = em.find(MemberEntity::class.java, 1L)
    println("Member Id = ${memberEntity.id}")
    println("Member Name = ${memberEntity.name}")
    return memberEntity
}

fun updateMember(member: MemberEntity, em: EntityManager) {
    val tx = em.transaction
    tx.begin()
    try {
        member.name = "HelloB"
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