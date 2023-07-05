package com.we.webackend.global.exception.data

open class BusinessException(
    val errorCode: ErrorCode,
    val data: String? = errorCode.message
): RuntimeException(errorCode.message) {
}
