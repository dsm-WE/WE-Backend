package com.we.webackend.domain.post.persistance.entity

import com.we.webackend.domain.user.persistance.entity.User
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@DiscriminatorValue("portfolio")
class Portfolio(
    title: String,
    user: User,
    content: String,
    file: File
): Post(
    title,
    user
) {

    @OneToOne(cascade = [CascadeType.DETACH])
    var file: File = file
        protected set

    var content: String = content

    @OneToMany(mappedBy = "targetPortfolio")
    val commentList: MutableList<Comment> = ArrayList()

    fun edit(request: EditPortfolioRequest) {
        request.title?.let {
            this.title = it
        }
        request.content?.let {
            this.content = it
        }
    }

    fun changePortfolioFile(file: File) {
        this.file = file
    }

    fun toMinimumPortfolioResponse(): MinimumPortfolioResponse {
        return MinimumPortfolioResponse(
            this.title,
            this.file,
            this.user.toUserResponse(),
            this.createdAt?: LocalDateTime.now(),
            this.updatedAt?: LocalDateTime.now(),
            this.commentList.size
        )
    }

    fun toMaximumPortfolioResponse(): MaximumPortfolioResponse {
        return MaximumPortfolioResponse(
            this.title,
            this.file,
            this.user.toUserResponse(),
            this.createdAt?: LocalDateTime.now(),
            this.updatedAt?: LocalDateTime.now(),
            this.commentList.map {
                it.toCommentResponse()
            },
            this.likeCount
        )
    }

}
