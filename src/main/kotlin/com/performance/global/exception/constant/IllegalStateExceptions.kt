package com.performance.global.exception.constant

enum class IllegalStateExceptions(
    private val message: String
) {
    RESERVATION_UNAVAILABLE("예약 불가");

    fun exception(): Exception {
        return IllegalStateException(message)
    }
}