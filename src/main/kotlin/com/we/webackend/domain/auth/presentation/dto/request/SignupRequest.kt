package com.we.webackend.domain.auth.presentation.dto.request

data class SignupRequest(
    val email: String,
    val password: String,
    val code: String
)
