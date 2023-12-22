package com.performance.domain.performance.service

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.repository.PerformanceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

class PerformanceService(
    private val performanceRepository: PerformanceRepository
) {
    fun getPerformances(pageable: Pageable): Page<PerformanceResponse> =
        performanceRepository.findAll(pageable)
            .map {
                PerformanceResponse(it)
            }

    fun getPerformance(id: Long): PerformanceResponse =
        PerformanceResponse(
            performanceRepository.findByIdOrNull(id) ?: throw Exception()
        )
}