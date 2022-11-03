package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Membership

object MembershipFixture {
    fun getMembership(
        code: String = "9860172011",
        userId: String = "123456789"
    ): Membership {
        return Membership(code, userId)
    }
}
