package com.performance.domain.reservation.dto

import com.performance.domain.reservation.constant.ReservationStatus
import jakarta.validation.constraints.Email
import org.jetbrains.annotations.NotNull

data class ReservationRequest(
    val id: Long,
    @field:Email
    var email: String,
    @field:NotNull
    var performanceId: Long,
    @field:NotNull
    var seatId: Long,
    @field:NotNull
    var status: ReservationStatus
)
