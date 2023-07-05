package com.we.webackend.domain.post.presentation.dto.response

import com.we.webackend.domain.user.presentation.dto.response.UserResponse

data class CommentResponse (
    val title: String,
    val user: UserResponse,
    val likeCount: Int
)
