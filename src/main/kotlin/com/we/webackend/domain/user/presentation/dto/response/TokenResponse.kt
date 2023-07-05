package com.we.webackend.domain.user.presentation.dto.response

data class TokenResponse (
    val accessToken: String,
    val refreshToken: String

)
