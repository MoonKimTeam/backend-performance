package com.performance.domain.place.dto

import com.performance.domain.place.model.Place
import com.performance.domain.seat.dto.SeatResponse

data class PlaceResponse(
    val id: Long,
    var name: String,
    var totalSeat: Int,
    var seats: List<SeatResponse>?
) {
    companion object {
        fun from(place: Place): PlaceResponse =
            PlaceResponse(
                id = place.id,
                name = place.name,
                totalSeat = place.totalSeat,
                seats = place.seats
                    ?.map
                    {
                        SeatResponse.from(it)
                    }
            )
    }
}