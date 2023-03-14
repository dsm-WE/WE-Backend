package com.we.webackend.domain.post.persistance.entity

import com.we.webackend.domain.auth.persistance.entity.User
import javax.persistence.Entity

@Entity
class Comment(
    title: String,
    user: User
): Post(
    title,
    user
) {



}