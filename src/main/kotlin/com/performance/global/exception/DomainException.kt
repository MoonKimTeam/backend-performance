package com.performance.global.exception

import com.performance.global.constant.ResponseCode

class DomainException(
    val code: ResponseCode
) : RuntimeException(code.message)