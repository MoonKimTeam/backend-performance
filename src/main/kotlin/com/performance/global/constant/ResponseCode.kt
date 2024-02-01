package com.performance.global.constant

import org.springframework.http.HttpStatus

enum class ResponseCode(
    val code: String,
    val message: String,
    val httpStatus: HttpStatus
) {
    SUCCESS("101", "성공", HttpStatus.OK),

    RESERVATION_NOT_AVAILABLE("201", "현재 예약 불가합니다.", HttpStatus.BAD_REQUEST),

    PERFORMANCE_NOT_EXIST("301", "공연이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    PLACE_NOT_EXIST("302", "공연장이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    RESERVATION_NOT_EXIST("303", "예약이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
}