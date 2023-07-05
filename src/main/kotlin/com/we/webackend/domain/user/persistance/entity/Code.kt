package com.we.webackend.domain.user.persistance.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed


@RedisHash
class Code(
    email: String,
    code: String
) {
    @Id
    var id: String = email + code
        protected set

    @Indexed
    var email: String = email
        protected set

    val code: String = code


}
