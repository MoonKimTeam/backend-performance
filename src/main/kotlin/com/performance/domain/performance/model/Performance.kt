package com.performance.domain.performance.model

import com.performance.domain.place.model.Place
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "performance")
class Performance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var place: Place,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "price", nullable = false)
    var price: BigDecimal,

    @Column(name = "start_at", nullable = false)
    var startAt: LocalDateTime,

    @Column(name = "end_at", nullable = false)
    var endAt: LocalDateTime,

    @Column(name = "reservation_start_at", nullable = false)
    var reservationStartAt: LocalDateTime,

    @Column(name = "reservation_end_at", nullable = false)
    var reservationEndAt: LocalDateTime,

    @Column(name = "can_reserve", nullable = false)
    var canReserve: Boolean,

    @Column(name = "available_seat", nullable = false)
    var availableSeat: Int
): BaseEntity()