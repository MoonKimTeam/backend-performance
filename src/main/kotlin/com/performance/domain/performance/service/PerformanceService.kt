package com.performance.domain.performance.service

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.place.repository.PlaceRepository
import com.performance.domain.seat.dto.SeatResponse
import com.performance.domain.seat.repository.SeatRepository
import com.performance.global.constant.ResponseCode
import com.performance.global.exception.DomainException
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository,
    private val placeRepository: PlaceRepository,
    private val seatRepository: SeatRepository
) {
    @Transactional(readOnly = true)
    fun getPerformances(pageable: Pageable): List<PerformanceResponse> {
        val performances = performanceRepository.findAll(pageable)
            .content

        return performances.map {
            val place = placeRepository.findByIdOrNull(it.placeId)
                ?: throw DomainException(ResponseCode.PLACE_NOT_EXIST)
            val seatResponses = seatRepository.findByPlaceId(place.id)
                .map { SeatResponse.from(it) }
            PerformanceResponse.from(it, place, seatResponses)
        }
    }

    @Transactional(readOnly = true)
    fun getPerformance(id: Long): PerformanceResponse {
        val performance = performanceRepository.findByIdOrNull(id)
            ?: throw DomainException(ResponseCode.PERFORMANCE_NOT_EXIST)
        val place = placeRepository.findByIdOrNull(performance.placeId)
            ?: throw DomainException(ResponseCode.PLACE_NOT_EXIST)
        val seatResponses = seatRepository.findByPlaceId(place.id)
            .map { SeatResponse.from(it) }
        return PerformanceResponse.from(performance, place, seatResponses)
    }
}