package com.performance.domain.performance.dto

import com.performance.domain.performance.model.Performance
import com.performance.domain.place.dto.PlaceResponse
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
        fun from(performance: Performance): PerformanceResponse =
            PerformanceResponse(
                performance.id,
                performance.name,
                performance.description,
                performance.price,
                performance.startAt,
                performance.endAt,
                performance.reservationStartAt,
                performance.reservationEndAt,
                performance.canReserve
                        && performance.reservationEndAt.isBefore(LocalDateTime.now())
                        && performance.reservationStartAt.isAfter(LocalDateTime.now()),
                performance.availableSeat,
                PlaceResponse.from(performance.place)
            )
    }
}