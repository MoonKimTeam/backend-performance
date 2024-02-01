package com.performance.domain.performance.dto

import com.performance.domain.performance.model.Performance
import com.performance.domain.place.dto.PlaceResponse
import com.performance.domain.place.model.Place
import com.performance.domain.seat.dto.SeatResponse
import java.math.BigDecimal
import java.time.LocalDateTime

data class PerformanceResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val startAt: LocalDateTime,
    val endAt: LocalDateTime,
    val reservationStartAt: LocalDateTime,
    val reservationEndAt: LocalDateTime,
    val canReserve: Boolean,
    val availableSeat: Int,
    val place: PlaceResponse
) {
    companion object {
        fun from(
            performance: Performance,
            place: Place,
            seatResponses: List<SeatResponse>?
        ): PerformanceResponse {
            return PerformanceResponse(
                id = performance.id,
                name = performance.name,
                description = performance.description,
                price = performance.price,
                startAt = performance.startAt,
                endAt = performance.endAt,
                reservationStartAt = performance.reservationStartAt,
                reservationEndAt = performance.reservationEndAt,
                canReserve = performance.canReserve(),
                availableSeat = performance.availableSeat,
                place = PlaceResponse.from(place, seatResponses)
            )
        }
    }
}