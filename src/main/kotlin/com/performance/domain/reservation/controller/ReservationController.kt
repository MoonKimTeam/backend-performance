package com.performance.domain.reservation.controller

import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/reservations")
class ReservationController(
    private val reservationService: ReservationService
) {
    @PostMapping
    fun saveReservation(@RequestBody @Validated request: ReservationRequest): ResponseEntity<Unit> =
        reservationService.saveReservation(request)
            .let {
                ResponseEntity.created(
                    URI.create("/reservations/$it")
                ).build()
            }
}