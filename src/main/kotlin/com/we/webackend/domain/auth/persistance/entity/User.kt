package com.we.webackend.domain.auth.persistance.entity

import com.we.webackend.domain.auth.presentation.dto.response.UserResponse
import com.we.webackend.domain.post.persistance.entity.Post
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
class User(
    name: String,
    email: String,
    password: String
): BaseTimeEntity() {

    @Id
    val email: String = email

    @Column(name = "password", nullable = false)
    val password = password

    @Column(name = "name", nullable = false)
    val name = name

    @OneToMany(mappedBy = "user")
    val postList: MutableList<Post> = ArrayList()

    fun toUserResponse(): UserResponse {
        return UserResponse(
            this.name,
            this.email
        )
    }

}