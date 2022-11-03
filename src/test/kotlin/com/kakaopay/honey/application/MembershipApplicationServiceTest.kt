package com.kakaopay.honey.application

import com.kakaopay.honey.domain.MembershipFactory
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.fixture.MembershipFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(value = [MembershipApplicationService::class])
class MembershipApplicationServiceTest(
    @Autowired private val membershipApplicationService: MembershipApplicationService
) {
    @MockkBean
    lateinit var membershipJpaRepository: MembershipJpaRepository

    @MockkBean
    lateinit var membershipFactory: MembershipFactory

    @Test
    fun `멤버십_코드를_생성할_수_있다`() {
        val expected = MembershipFixture.getMembership()
        every { membershipFactory.create(any()) } returns expected
        every { membershipJpaRepository.save(any()) } returns expected
        val actual = membershipApplicationService.createMembership(expected.userId)
        actual shouldBe expected
    }
}
