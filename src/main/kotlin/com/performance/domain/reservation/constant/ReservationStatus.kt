package com.performance.domain.reservation.constant

enum class ReservationStatus(
    private val text: String
) {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    fun get(): String {
        return text
    }
}