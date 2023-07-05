package com.we.webackend.domain.post.presentation.dto.response

import com.we.webackend.domain.user.presentation.dto.response.UserResponse
import com.we.webackend.domain.post.persistance.entity.File
import java.time.LocalDateTime

data class MinimumPortfolioResponse(
    val title: String,
    val photoList: File,
    val uploader: UserResponse,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val commentCount: Int
)
