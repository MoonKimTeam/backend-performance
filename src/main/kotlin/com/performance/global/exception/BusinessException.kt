package com.performance.global.exception

import com.performance.global.constant.ResponseCode

class BusinessException(
    val code: ResponseCode
) : RuntimeException(code.message)