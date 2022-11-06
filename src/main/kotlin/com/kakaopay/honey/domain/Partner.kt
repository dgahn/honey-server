package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Partner(
    @Id
    @GeneratedValue
    val id: Long = 0,
    val name: String,
    @Enumerated(EnumType.STRING)
    val category: Category
) {
    override fun toString(): String {
        return "Partner(id=$id, name='$name', category=$category)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Partner

        return id != other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
