package com.kakaopay.honey.application.unit

import com.kakaopay.honey.application.MembershipApplicationService
import com.kakaopay.honey.domain.MembershipFactory
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.exception.CreateMembershipFailException
import com.kakaopay.honey.fixture.MembershipFixture
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.Optional

@ExtendWith(SpringExtension::class)
@Import(value = [MembershipApplicationService::class])
class MembershipApplicationServiceUnitTest(
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
        every { membershipJpaRepository.findById(any()) } returns Optional.empty()
        every { membershipJpaRepository.findByCode(any()) } returns null
        every { membershipJpaRepository.save(any()) } returns expected
        val actual = membershipApplicationService.createMembership(expected.userId)
        actual shouldBe expected
    }

    @Test
    fun `이미_발급된_멤버십_코드인_경우_코드를_최대_5번_다시_생성한다`() {
        val duplicatedMembership = MembershipFixture.getMembership()
        every { membershipFactory.create(any()) } returns duplicatedMembership
        every { membershipJpaRepository.findById(any()) } returns Optional.empty()
        every { membershipJpaRepository.findByCode(any()) } returns duplicatedMembership

        shouldThrow<CreateMembershipFailException> {
            membershipApplicationService.createMembership(duplicatedMembership.userId)
        }

        verify(exactly = 5) { membershipFactory.create(any()) }
    }

    @Test
    fun `이미_발급한_멤버십_코드가_있는_유저는_기존_멤버십_코드를_전달한다`() {
        val expected = MembershipFixture.getMembership()
        every { membershipJpaRepository.findById(any()) } returns Optional.of(expected)

        val actual = membershipApplicationService.createMembership(expected.userId)
        actual shouldBe expected
    }
}
