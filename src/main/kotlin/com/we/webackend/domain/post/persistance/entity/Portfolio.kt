package com.we.webackend.domain.post.persistance.entity

import com.we.webackend.domain.auth.persistance.entity.User
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import javax.persistence.*


@Entity
class Portfolio(
    title: String,
    user: User,
    fileList: MutableList<File>,
    content: String
): Post(
    title,
    user
) {

    @OneToMany
    val fileList: MutableList<File> = fileList

    val content: String = content

    fun toMinimumPortfolioResponse(): MinimumPortfolioResponse {
        return MinimumPortfolioResponse(
            this.title,
            this.fileList,
            this.user.toUserResponse()
        )
    }


}