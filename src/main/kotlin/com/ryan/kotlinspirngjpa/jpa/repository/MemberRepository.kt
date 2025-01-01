package com.ryan.kotlinspirngjpa.jpa.repository

import com.ryan.kotlinspirngjpa.jpa.entity.MemberEntity
import jakarta.persistence.EntityManager

class MemberRepository(private val em: EntityManager) {

    fun save(member: MemberEntity): MemberEntity {
        this.em.transaction.begin()
        try {
            this.em.persist(member)
            this.em.transaction.commit()
        } catch (e: Exception) {
            this.em.transaction.rollback()
        }

        return member
    }

    fun findById(id: Long): MemberEntity? {
        return this.em.find(MemberEntity::class.java, id)
    }

    fun update(member: MemberEntity) {
        this.em.transaction.begin()
        try {
            this.em.persist(member)
            this.em.transaction.commit()
        } catch (e: Exception) {
            this.em.transaction.rollback()
        }
    }

    fun delete(member: MemberEntity) {
        this.em.transaction.begin()
        try {
            this.em.remove(member)
            this.em.transaction.commit()
        } catch (e: Exception) {
            this.em.transaction.rollback()
        }
    }

    fun setDetach(member: MemberEntity) {
        this.em.detach(member)
    }
}