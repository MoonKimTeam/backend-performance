package com.performance.domain.reservation.aspect

import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.repository.ReservationRepository
import com.performance.global.exception.constant.IllegalStateExceptions
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Aspect
@Component
class ReservationAspect(
    private val performanceRepository: PerformanceRepository,
    private val reservationRepository: ReservationRepository
) {
    @Around("execution(* com..ReservationService.saveReservation(..)) && args(request, ..)")
    fun checkReservationAvailability(joinPoint: ProceedingJoinPoint, request: ReservationRequest): Any =
        joinPoint
            .takeIf {
                val performance =
                    performanceRepository.findByIdOrNull(request.performanceId) ?: throw IllegalStateException()
                performance.canReserve
                        && performance.reservationEndAt.isAfter(LocalDateTime.now())
                        && performance.reservationStartAt.isBefore(LocalDateTime.now())
            }.takeIf {
                reservationRepository.findByPerformanceIdAndSeatId(
                    request.performanceId,
                    request.seatId
                ).isEmpty()
            }?.proceed()
            ?: throw IllegalStateExceptions.RESERVATION_UNAVAILABLE.exception()
}