package com.performance.domain.performance.controller

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.service.PerformanceService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/performances")
class PerformanceController(
    private val performanceService: PerformanceService
) {
    @GetMapping
    fun getPerformances(pageable: Pageable): ResponseEntity<Page<PerformanceResponse>> =
        ResponseEntity.ok(performanceService.getPerformances(pageable))

    @GetMapping("/{id}")
    fun getPerformance(@PathVariable id: Long): ResponseEntity<PerformanceResponse> =
        ResponseEntity.ok(performanceService.getPerformance(id))
}