package com.performance.domain.performance.dto

import com.performance.domain.performance.model.Performance
import com.performance.domain.seat.dto.SeatResponse
import java.math.BigDecimal
import java.time.LocalDateTime

data class PerformanceResponse(
    private val id: Long,
    private val name: String,
    private val description: String,
    private val price: BigDecimal,
    private val startAt: LocalDateTime,
    private val endAt: LocalDateTime,
    private val reservationStartAt: LocalDateTime,
    private val reservationEndAt: LocalDateTime,
    private val canReserve: Boolean,
    private val availableSeat: Int,
    private val placeId: Long,
    private val totalSeat: Int,
    private val seats: List<SeatResponse>
) {
    constructor(performance: Performance) : this(
        performance.id,
        performance.name,
        performance.description,
        performance.price,
        performance.startAt,
        performance.endAt,
        performance.reservationStartAt,
        performance.reservationEndAt,
        performance.canReserve,
        performance.availableSeat,
        performance.place.id,
        performance.place.totalSeat,
        performance.place
            .seats
            .map {
                SeatResponse(
                    it.id,
                    it.seatNumber,
                    it.isAvailable
                )
            }
    )
}