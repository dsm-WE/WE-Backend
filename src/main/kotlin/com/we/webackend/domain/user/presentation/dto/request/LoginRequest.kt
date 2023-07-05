package com.we.webackend.domain.user.presentation.dto.request

data class LoginRequest(
    val email: String,
    val password: String
)
