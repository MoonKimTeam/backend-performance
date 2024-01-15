package com.performance.domain.performance.service

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.repository.PerformanceRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PerformanceService(
    private val performanceRepository: PerformanceRepository
) {

    @Transactional(readOnly = true)
    fun getPerformances(pageable: Pageable): List<PerformanceResponse> =
        performanceRepository.findAll(pageable)
            .content
            .map {
                PerformanceResponse.from(it)
            }

    @Transactional(readOnly = true)
    fun getPerformance(id: Long): PerformanceResponse =
        PerformanceResponse.from(
            performanceRepository.findByIdOrNull(id)
                ?: throw IllegalStateException()
        )
}