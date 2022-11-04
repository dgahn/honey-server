package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Point(
    @Id
    val id: Long = 0
)
