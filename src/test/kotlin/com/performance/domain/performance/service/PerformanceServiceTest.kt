package com.performance.domain.performance.service

import com.performance.domain.performance.dto.PerformanceResponse
import com.performance.domain.performance.model.Performance
import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.place.model.Place
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class PerformanceServiceTest {
    private val performance: Performance = Performance(
        id = 1,
        place = Place(
            id = 1,
            name = "공연장1",
            totalSeat = 100
        ),
        name = "공연1",
        description = "설명1",
        price = 100000.toBigDecimal(),
        startAt = LocalDateTime.of(2023, 12, 25, 19, 0, 0),
        endAt = LocalDateTime.of(2023, 12, 25, 22, 0, 0),
        reservationStartAt = LocalDateTime.of(2023, 12, 1, 0, 0, 0, 0),
        reservationEndAt = LocalDateTime.of(2023, 12, 8, 0, 0, 0, 0),
        true,
        100
    )

    private val performanceResponse: PerformanceResponse = PerformanceResponse(performance)

    @Mock
    private lateinit var performanceRepository: PerformanceRepository

    @InjectMocks
    private lateinit var performanceService: PerformanceService

    @Test
    fun getPerformances() {
        `when`(performanceRepository.findAll(any(Pageable::class.java)))
            .thenReturn(PageImpl(listOf(performance)))

        assertThat(performanceService.getPerformances(PageRequest.of(1, 10)))
            .isEqualTo(PageImpl(listOf(performanceResponse)))
    }

    @Test
    fun getPerformance() {
        `when`(performanceRepository.findById(anyLong()))
            .thenReturn(Optional.of(performance))

        assertThat(performanceService.getPerformance(1))
            .isEqualTo(PerformanceResponse(performance))
    }
}