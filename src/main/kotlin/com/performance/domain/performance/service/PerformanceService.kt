package com.performance.domain.performance.service

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.model.Performance
import com.performance.domain.performance.repository.PerformanceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull

class PerformanceService(
    private val performanceRepository: PerformanceRepository
) {
    fun getPerformances(pageable: Pageable): Page<PerformanceResponse> {
        val performances: Page<Performance> = performanceRepository.findAll(pageable)
        return performances.map {
            PerformanceResponse(it)
        }
    }

    fun getPerformance(id: Long): PerformanceResponse {
        val performance = performanceRepository.findByIdOrNull(id) ?: throw Exception()
        return PerformanceResponse(performance)
    }
}