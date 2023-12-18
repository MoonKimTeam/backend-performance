package com.performance.domain.seat.model

import com.performance.domain.place.model.Place
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "SEAT")
class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    var place: Place,

    @Column(name = "seat_number", nullable = false)
    var seatNumber: String,

    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean
): BaseEntity()