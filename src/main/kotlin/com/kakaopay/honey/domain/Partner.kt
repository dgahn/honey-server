package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Partner(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    val category: Category
)
