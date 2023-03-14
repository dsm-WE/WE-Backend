package com.we.webackend.domain.auth.presentation.dto.request

data class CheckCodeRequest(
    val email: String,
    val code: String
)
