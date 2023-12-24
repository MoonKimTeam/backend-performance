package com.performance.domain.place.model

import com.performance.domain.seat.model.Seat
import com.performance.global.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "place")
class Place(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "total_seat", nullable = false)
    var totalSeat: Int,

    @OneToMany(mappedBy = "place")
    var seats: List<Seat>?
) : BaseEntity()