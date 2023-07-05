package com.we.webackend.domain.user.presentation.dto.request

data class SignupRequest(
    val name: String,
    val email: String,
    val password: String,
    val code: String,
    val introduction: String
)
