package com.we.webackend.domain.auth.presentation.dto.response

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String

)