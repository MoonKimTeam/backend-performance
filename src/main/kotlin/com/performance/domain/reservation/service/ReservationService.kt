package com.performance.domain.reservation.service

import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.model.Reservation
import com.performance.domain.reservation.repository.ReservationRepository
import com.performance.global.constant.ResponseCode
import com.performance.global.exception.BusinessException
import com.performance.global.exception.DomainException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val performanceRepository: PerformanceRepository,
) {
    @Transactional
    fun saveReservation(request: ReservationRequest): Long {
        check(!unavailableReservation(request)) { throw BusinessException(ResponseCode.RESERVATION_NOT_AVAILABLE) }

        return reservationRepository.save(
            Reservation(
                id = request.id,
                email = request.email,
                performanceId = request.performanceId,
                seatId = request.seatId,
                status = request.status
            )
        ).id
    }

    private fun unavailableReservation(request: ReservationRequest): Boolean {
        val performance = performanceRepository.findByIdOrNull(request.performanceId)
            ?: throw DomainException(ResponseCode.PERFORMANCE_NOT_EXIST)
        return performance.canReserve() &&
                reservationRepository.findByPerformanceIdAndSeatId(
                    request.performanceId,
                    request.seatId
                ).isEmpty()
    }

    @Transactional
    fun updateReservation(request: ReservationRequest) {
        val reservation = reservationRepository.findByIdOrNull(request.id)
            ?: throw DomainException(ResponseCode.RESERVATION_NOT_EXIST)
        performanceRepository.findByIdOrNull(request.performanceId)
            ?: throw DomainException(ResponseCode.PERFORMANCE_NOT_EXIST)

        reservation.let {
            it.email = request.email
            it.performanceId = request.performanceId
            it.seatId = request.seatId
            it.status = request.status
        }
    }

    @Transactional
    fun deleteReservation(id: Long) {
        val reservation = reservationRepository.findByIdOrNull(id)
            ?: throw DomainException(ResponseCode.RESERVATION_NOT_AVAILABLE)
        reservation.delete()
    }
}