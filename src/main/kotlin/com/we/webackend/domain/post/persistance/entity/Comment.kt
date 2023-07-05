package com.we.webackend.domain.post.persistance.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.we.webackend.domain.user.persistance.entity.User
import com.we.webackend.domain.post.presentation.dto.response.CommentResponse
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@DiscriminatorValue("comment")
class Comment(
    title: String,
    user: User,
    portfolio: Portfolio
): Post(
    title,
    user
) {

    @ManyToOne
    @JoinColumn(name = "target_post_id")
    @JsonIgnore
    val targetPortfolio: Portfolio = portfolio

    fun toCommentResponse(): CommentResponse {
        return CommentResponse(
            this.title,
            this.user.toUserResponse(),
            this.likeCount
        )
    }

}
