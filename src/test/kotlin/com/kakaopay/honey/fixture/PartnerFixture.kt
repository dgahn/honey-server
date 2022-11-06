package com.kakaopay.honey.fixture

import com.kakaopay.honey.domain.Category
import com.kakaopay.honey.domain.Partner

object PartnerFixture {
    fun getPartner(): Partner {
        return Partner(
            name = "partner_A",
            category = Category.A
        )
    }
}
