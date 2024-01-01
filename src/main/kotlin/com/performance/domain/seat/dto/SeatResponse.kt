package com.performance.domain.seat.dto

import com.performance.domain.seat.model.Seat

data class SeatResponse(
    val id: Long,
    val seatNumber: String,
    val isAvailable: Boolean
) {
    companion object {
        fun from(seat: Seat): SeatResponse =
            SeatResponse(
                id = seat.id,
                seatNumber = seat.seatNumber,
                isAvailable = seat.isAvailable
            )
    }
}