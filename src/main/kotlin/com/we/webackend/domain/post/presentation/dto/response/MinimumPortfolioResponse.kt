package com.we.webackend.domain.post.presentation.dto.response

import com.we.webackend.domain.auth.presentation.dto.response.UserResponse
import com.we.webackend.domain.post.persistance.entity.File

data class MinimumPortfolioResponse(
    val title: String,
    val photoList: List<File>,
    val uploader: UserResponse
)
