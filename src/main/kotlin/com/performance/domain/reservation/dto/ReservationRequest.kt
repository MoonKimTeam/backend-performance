package com.performance.domain.reservation.dto

import com.performance.domain.reservation.constant.ReservationStatus
import jakarta.validation.constraints.Email
import org.jetbrains.annotations.NotNull

data class ReservationRequest(
    val id: Long,
    @field:Email
    val email: String,
    @field:NotNull
    val performanceId: Long,
    @field:NotNull
    val seatId: Long,
    @field:NotNull
    val status: ReservationStatus
)
