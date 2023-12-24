package com.performance.domain.performance.repository

import com.performance.domain.performance.model.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository : JpaRepository<Performance, Long>