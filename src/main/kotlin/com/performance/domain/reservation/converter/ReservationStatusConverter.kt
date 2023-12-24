package com.performance.domain.reservation.converter

import com.performance.domain.reservation.constant.ReservationStatus
import jakarta.persistence.AttributeConverter

class ReservationStatusConverter : AttributeConverter<ReservationStatus, String> {
    override fun convertToDatabaseColumn(attribute: ReservationStatus?): String =
        attribute?.name
            ?: throw IllegalArgumentException()

    override fun convertToEntityAttribute(dbData: String?): ReservationStatus =
        ReservationStatus.valueOf(
            dbData
                ?: throw IllegalArgumentException()
        )
}