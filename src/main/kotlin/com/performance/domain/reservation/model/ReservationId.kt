package com.performance.domain.reservation.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class ReservationId(
    @Column
    val id: Long,

    @Column(name = "user_id", nullable = false)
    var userId: Long,
) : Serializable
