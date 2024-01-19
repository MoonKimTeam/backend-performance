package com.performance.domain.reservation.model

import com.performance.domain.reservation.constant.ReservationStatus
import com.performance.domain.reservation.constant.converter.ReservationStatusConverter
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "reservation")
class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var email: String,

    @Column(name = "performance_id", nullable = false)
    var performanceId: Long,

    @Column(name = "seat_id", nullable = false)
    var seatId: Long,

    @Column(name = "status", nullable = false)
    @Convert(converter = ReservationStatusConverter::class)
    var status: ReservationStatus
) : BaseEntity()