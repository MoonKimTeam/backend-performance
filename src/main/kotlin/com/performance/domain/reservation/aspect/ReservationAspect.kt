package com.performance.domain.reservation.aspect

import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.global.exception.ReservationUnavailableException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Aspect
@Component
class ReservationAspect(
    private val performanceRepository: PerformanceRepository
) {
    @Around("execution(* com..reservation.service.saveReservation()) && args(request, ..)")
    fun checkReservationAvailability(joinPoint: ProceedingJoinPoint, request: ReservationRequest): Any {
        return joinPoint.proceed()
            .takeIf {
                performanceRepository.findByIdOrNull(
                    request.performanceId
                )?.canReserve
                    ?: throw IllegalArgumentException()
            } ?: throw ReservationUnavailableException()
    }
}