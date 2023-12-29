package com.performance.domain.place.dto

import com.performance.domain.place.model.Place
import com.performance.domain.seat.dto.SeatResponse

data class PlaceResponse(
    val id: Long,
    var name: String,
    var totalSeat: Int,
    var seats: List<SeatResponse>?
) {
    constructor(place: Place) : this(
        place.id,
        place.name,
        place.totalSeat,
        place.seats
            ?.map {
                SeatResponse(it)
            }
    )
}