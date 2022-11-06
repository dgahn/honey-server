package com.kakaopay.honey.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Membership(
    @Id
    val code: String,
    val userId: String
) {

    override fun toString(): String {
        return "Membership(code='$code', userId='$userId')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Membership
        return code == other.code
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }
}
