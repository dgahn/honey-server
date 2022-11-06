package com.kakaopay.honey.application.integration

import com.kakaopay.honey.application.PointApplicationService
import com.kakaopay.honey.domain.Membership
import com.kakaopay.honey.domain.MembershipJpaRepository
import com.kakaopay.honey.domain.Partner
import com.kakaopay.honey.domain.PartnerJpaRepository
import com.kakaopay.honey.domain.Point
import com.kakaopay.honey.domain.PointJpaRepository
import com.kakaopay.honey.fixture.MembershipFixture
import com.kakaopay.honey.fixture.PartnerFixture
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class PointApplicationServiceIntegrationTest(
    @Autowired private val pointApplicationService: PointApplicationService,
    @Autowired private val partnerJpaRepository: PartnerJpaRepository,
    @Autowired private val membershipJpaRepository: MembershipJpaRepository,
    @Autowired private val pointJpaRepository: PointJpaRepository,
) {
    lateinit var membership: Membership
    lateinit var partner: Partner
    lateinit var point: Point

    @BeforeEach
    fun setup() {
        partner = partnerJpaRepository.save(PartnerFixture.getPartner())
        membership = membershipJpaRepository.save(MembershipFixture.getMembership())
        point = pointApplicationService.earnPoint(0, membership.code, partner.id)
    }

    @AfterEach
    fun clear() {
        partnerJpaRepository.deleteAll()
        membershipJpaRepository.deleteAll()
        pointJpaRepository.deleteAll()
    }

    @Test
    fun `포인트를_빠르게_여러번_적립할_수_있다`() = runBlocking {
        val membershipCode = membership.code
        val partnerId = partner.id
        val originalPoint = point.totalPoint
        val repeat = 5
        repeat((1..repeat).count()) {
            delay(100L)
            CoroutineScope(Dispatchers.IO).launch {
                pointApplicationService.earnPoint(10, membershipCode, partnerId)
            }
        }
        delay(2_000)
        pointJpaRepository.findAll().first().totalPoint shouldBe originalPoint + (10 * repeat)
    }
}
