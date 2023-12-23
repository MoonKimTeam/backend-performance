package com.performance.domain.reservation.service

import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.model.Reservation
import com.performance.domain.reservation.repository.ReservationRepository
import com.performance.domain.seat.repository.SeatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

open class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val performanceRepository: PerformanceRepository,
    private val seatRepository: SeatRepository
) {
    @Transactional
    open fun saveReservation(request: ReservationRequest) =
        reservationRepository.save(
            Reservation(
                request.id,
                request.email,
                performanceRepository.findByIdOrNull(request.performanceId) ?: throw Exception(),
                seatRepository.findByIdOrNull(request.seatId) ?: throw Exception(),
                request.status
            )
        )
}