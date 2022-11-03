package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Membership(
    @Id
    val code: String
)
