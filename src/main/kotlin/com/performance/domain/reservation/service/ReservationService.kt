package com.performance.domain.reservation.service

import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.model.Reservation
import com.performance.domain.reservation.repository.ReservationRepository
import com.performance.domain.seat.repository.SeatRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val performanceRepository: PerformanceRepository,
    private val seatRepository: SeatRepository,
) {
    @Transactional
    fun saveReservation(request: ReservationRequest) {
        if (unavailableReservation(request)) {
            return
        }

        reservationRepository.save(
            Reservation(
                id = request.id,
                email = request.email,
                performance = performanceRepository.findByIdOrNull(request.performanceId)
                    ?: throw IllegalStateException(),
                seat = seatRepository.findByIdOrNull(request.seatId)
                    ?: throw IllegalStateException(),
                status = request.status
            )
        )
    }

    private fun unavailableReservation(request: ReservationRequest): Boolean {
        val performance =
            performanceRepository.findByIdOrNull(request.performanceId) ?: throw IllegalStateException()
        return performance.canReserve
                && performance.reservationEndAt.isAfter(LocalDateTime.now())
                && performance.reservationStartAt.isBefore(LocalDateTime.now())
                &&
                reservationRepository.findByPerformanceIdAndSeatId(
                    request.performanceId,
                    request.seatId
                ).isEmpty()
    }
}