package com.performance.domain.reservation.model

import com.performance.domain.performance.model.Performance
import com.performance.domain.seat.model.Seat
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "reservation")
class Reservation(
    @Id
    val id: Long = 0,

    @Column
    var email: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "performance_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var performance: Performance,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_number", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var seat: Seat,

    @Column(name = "is_paid", nullable = false)
    var isPaid: Boolean
) : BaseEntity()