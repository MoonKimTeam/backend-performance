package com.performance.domain.performance.dto

import com.performance.domain.performance.model.Performance
import com.performance.domain.place.model.Place
import java.math.BigDecimal
import java.time.LocalDateTime

data class PerformanceResponse(
    private val id: Long,
    private val place: Place,
    private val name: String,
    private val description: String,
    private val price: BigDecimal,
    private val startAt: LocalDateTime,
    private val endAt: LocalDateTime,
    private val reservationStartAt: LocalDateTime,
    private val reservationEndAt: LocalDateTime,
    private val canReserve: Boolean,
    private val availableSeat: Int
) {
    constructor(performance: Performance) : this(
        performance.id,
        performance.place,
        performance.name,
        performance.description,
        performance.price,
        performance.startAt,
        performance.endAt,
        performance.reservationStartAt,
        performance.reservationEndAt,
        performance.canReserve,
        performance.availableSeat
    )
}