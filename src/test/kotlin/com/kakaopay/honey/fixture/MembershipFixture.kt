package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Membership

object MembershipFixture {
    fun getMembership(): Membership {
        return Membership("9860172011")
    }
}
