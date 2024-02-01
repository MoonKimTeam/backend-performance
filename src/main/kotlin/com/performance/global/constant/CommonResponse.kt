package com.performance.global.constant

class CommonResponse<T>(
    val code: String,
    val message: String,
    val result: T?
) {
    companion object {
        fun fail(): CommonResponse<Unit> {
            return CommonResponse(
                code = ResponseCode.SUCCESS.code,
                message = ResponseCode.SUCCESS.message,
                result = null
            )
        }

        fun fail(errorCode: ResponseCode): CommonResponse<Unit> {
            return CommonResponse(
                code = errorCode.code,
                message = errorCode.message,
                result = null
            )
        }
    }
}