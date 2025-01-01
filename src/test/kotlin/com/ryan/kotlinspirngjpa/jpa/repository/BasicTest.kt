package com.ryan.kotlinspirngjpa.jpa.repository

import com.ryan.kotlinspirngjpa.jpa.entity.MemberEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.junit.jupiter.api.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class BasicTest {
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
    fun `Create 데이터 저장`() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        val member = MemberEntity(name = "HelloA")

        // when(실행): 어떠한 함수를 실행하면
        val saveMember = this.memberRepository.save(member)

        // then(검증): 어떠한 결과가 나와야 한다.
        assertNotNull(saveMember)
        assertEquals(saveMember.id, 1L)
        assertEquals(saveMember.name, member.name)
    }

    @Test
    fun `Read 데이터 조회`() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        val member = MemberEntity(name = "HelloA")
        this.memberRepository.save(member)

        // when(실행): 어떠한 함수를 실행하면
        val findMember = this.memberRepository.findById(1L)

        // then(검증): 어떠한 결과가 나와야 한다.
        assertNotNull(findMember)
        assertEquals(findMember.id, 1L)
        assertEquals(findMember.name, member.name)
    }

    @Test
    fun `Update 데이터 수정`() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        val member = MemberEntity(name = "HelloA")
        this.memberRepository.save(member)

        val findMember = this.memberRepository.findById(1L)
        findMember!!.name = "HelloB"

        // when(실행): 어떠한 함수를 실행하면
        this.memberRepository.update(findMember)

        // then(검증): 어떠한 결과가 나와야 한다.
        val updateMember = this.memberRepository.findById(1L)!!
        assertEquals(findMember.name, updateMember.name)
    }


    @Test
    fun `Delete 데이터 삭제`() {
        // given(준비): 어떠한 데이터가 준비되었을 때
        val member = MemberEntity(name = "HelloA")
        this.memberRepository.save(member)

        val findMember = this.memberRepository.findById(1L)

        // when(실행): 어떠한 함수를 실행하면
        this.memberRepository.deleteById(findMember!!)

        // then(검증): 어떠한 결과가 나와야 한다.
        val deletedUser = this.memberRepository.findById(1L)
        assertNull(deletedUser)
    }
}