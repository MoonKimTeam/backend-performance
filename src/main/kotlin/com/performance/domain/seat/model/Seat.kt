package com.performance.domain.seat.model

import com.performance.domain.place.model.Place
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "seat")
class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var place: Place,

    @Column(name = "seat_number", nullable = false)
    var seatNumber: String,

    @Column(name = "is_available", nullable = false)
    var isAvailable: Boolean
): BaseEntity()