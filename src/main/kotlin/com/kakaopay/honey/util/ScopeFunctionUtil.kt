package com.kakaopay.honey.util

inline fun <T> T.after(block: T.() -> Unit): T {
    block()
    return this
}
