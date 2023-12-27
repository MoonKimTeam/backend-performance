package com.performance.domain.reservation.repository

import com.performance.domain.reservation.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findByPerformanceIdAndSeatId(performanceId: Long, seatId: Long): List<Reservation>
}