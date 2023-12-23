package com.performance.domain.reservation.dto

import com.performance.domain.reservation.constant.ReservationStatus

data class ReservationRequest(
    val id: Long,
    var email: String,
    var performanceId: Long,
    var seatId: Long,
    var status: ReservationStatus
)
