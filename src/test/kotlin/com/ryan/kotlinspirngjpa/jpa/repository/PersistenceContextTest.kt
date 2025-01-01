package com.ryan.kotlinspirngjpa.jpa.repository

import com.ryan.kotlinspirngjpa.jpa.entity.MemberEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertNotEquals

class PersistenceContextTest {
    private lateinit var emf: EntityManagerFactory
    private lateinit var em: EntityManager
    private lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun setUp() {
        this.emf = Persistence.createEntityManagerFactory("ryan")
        this.em = this.emf.createEntityManager()
        this.memberRepository = MemberRepository(this.em)
    }

    @AfterEach
    fun closeEntityManager() {
        if (::em.isInitialized) {
            em.close()
        }

        if (::emf.isInitialized) {
            emf.close()
        }
    }

    @Test
    fun `Detach 준영속 상태 만들기`() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        val member = MemberEntity(name = "HelloA")
        this.memberRepository.save(member)

        val findMember = this.memberRepository.findById(1L)
        findMember!!.name = "HelloB"

        // when(실행): 어떠한 함수를 실행하면
        this.memberRepository.setDetach(findMember)

        // then(검증): 어떠한 결과가 나와야 한다.
        val updateMember = this.memberRepository.findById(1L)!!

        //updateMember.name 값은 HelloA
        assertNotEquals(findMember.name, updateMember.name)
    }
}