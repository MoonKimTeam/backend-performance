package com.performance.domain.reservation.converter

import com.performance.domain.reservation.constant.ReservationStatus
import jakarta.persistence.AttributeConverter

class ReservationStatusConverter : AttributeConverter<ReservationStatus, String> {
    override fun convertToDatabaseColumn(attribute: ReservationStatus?): String {
        return attribute?.name ?: throw Exception()
    }

    override fun convertToEntityAttribute(dbData: String?): ReservationStatus {
        return ReservationStatus.valueOf(dbData ?: throw Exception())
    }
}