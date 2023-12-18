package com.performance.domain.reservation.model

import com.performance.domain.performance.model.Performance
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "RESERVATION")
class Reservation(
    @Id
    val id: Long,

    @Id
    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    var performance: Performance,

    @Column(name = "seat_number", nullable = false)
    var seatNumber: String,

    @Column(name = "is_paid", nullable = false)
    var isPaid: Boolean
): BaseEntity()