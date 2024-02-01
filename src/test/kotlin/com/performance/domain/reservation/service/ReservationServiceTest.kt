package com.performance.domain.reservation.service

import com.performance.domain.performance.model.Performance
import com.performance.domain.performance.repository.PerformanceRepository
import com.performance.domain.reservation.constant.ReservationStatus
import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.model.Reservation
import com.performance.domain.reservation.repository.ReservationRepository
import com.performance.domain.seat.model.Seat
import com.performance.domain.seat.repository.SeatRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class ReservationServiceTest {
    private val reservation = Reservation(
        id = 1,
        email = "abc@example.com",
        performanceId = 1,
        seatId = 1,
        status = ReservationStatus.PENDING
    )
    private val performance = Performance(
        1,
        placeId = 1,
        name = "공연1",
        description = "공연1입니다.",
        price = 100000.toBigDecimal(),
        startAt = LocalDateTime.of(2023, 12, 31, 19, 0, 0),
        endAt = LocalDateTime.of(2023, 12, 31, 21, 0, 0),
        availableSeat = 100,
        canReserve = true,
        reservationStartAt = LocalDate.of(2023, 12, 1).atStartOfDay(),
        reservationEndAt = LocalDate.of(2023, 12, 7).atTime(23, 59, 59),
    )

    private val seat = Seat(
        id = 1,
        placeId = 1,
        seatNumber = "A1",
        isAvailable = true
    )

    @Mock
    private lateinit var performanceRepository: PerformanceRepository

    @Mock
    private lateinit var seatRepository: SeatRepository

    @Mock
    private lateinit var reservationRepository: ReservationRepository

    @InjectMocks
    private lateinit var reservationService: ReservationService

    @Test
    fun saveReservation() {
        `when`(reservationRepository.save(any(Reservation::class.java)))
            .thenReturn(reservation)
        `when`(performanceRepository.findById(anyLong()))
            .thenReturn(Optional.of(performance))
        `when`(seatRepository.findById(anyLong()))
            .thenReturn(Optional.of(seat))

        assertThat(
            reservationService.saveReservation(
                ReservationRequest(
                    id = 1,
                    email = "abc@example.com",
                    performanceId = 1,
                    seatId = 1,
                    status = ReservationStatus.PENDING
                )
            )
        ).isEqualTo(reservation)
    }
}