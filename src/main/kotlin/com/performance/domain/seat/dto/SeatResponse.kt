package com.performance.domain.seat.dto

import com.performance.domain.seat.model.Seat

data class SeatResponse(
    val id: Long,
    val seatNumber: String,
    val isAvailable: Boolean
) {
    constructor(seat: Seat) : this(
        id = seat.id,
        seatNumber = seat.seatNumber,
        isAvailable = seat.isAvailable
    )
}