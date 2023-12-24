package com.performance.domain.seat.repository

import com.performance.domain.seat.model.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>