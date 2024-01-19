package com.performance.domain.reservation.controller

import com.performance.domain.reservation.dto.ReservationRequest
import com.performance.domain.reservation.service.ReservationService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/reservations")
class ReservationController(
    private val reservationService: ReservationService
) {
    @PostMapping
    fun saveReservation(@RequestBody @Validated request: ReservationRequest): ResponseEntity<Unit> {
        return reservationService.saveReservation(request)
            .let {
                val uri = URI.create("/reservations/$it")
                ResponseEntity.created(uri)
                    .build()
            }
    }

    @PutMapping
    fun updateReservation(@RequestBody @Validated request: ReservationRequest): ResponseEntity<Unit> {
        reservationService.updateReservation(request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteReservation(id: Long): ResponseEntity<Unit> {
        reservationService.deleteReservation(id)
        return ResponseEntity.noContent().build()
    }
}