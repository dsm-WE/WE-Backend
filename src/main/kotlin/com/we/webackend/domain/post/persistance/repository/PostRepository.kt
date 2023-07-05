package com.we.webackend.domain.post.persistance.repository

import com.we.webackend.domain.post.persistance.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface PostRepository: JpaRepository<Post, Long> {

    @Query(nativeQuery = true, value = "select * from post where user_email = :userEmail and id = :id")
    fun findByIdAndUser(@Param(value = "id") id: Long, @Param(value = "userEmail") userEmail: String): Optional<Post>

    @Query(nativeQuery = true, value = "delete from post where post_id = :postId")
    @Modifying
    fun deleteByPostId(@Param(value = "postId") postId: Long)
}
