package com.we.webackend.domain.auth.persistance.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed


@RedisHash
class Code(
    email: String,
    code: String
) {
    @Id
    val id: String = email + code

    @Indexed
    val email: String = email

    val code: String = code


}