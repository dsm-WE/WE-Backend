package com.we.webackend.domain.post.persistance.entity

import com.we.webackend.domain.auth.persistance.entity.BaseTimeEntity
import com.we.webackend.domain.auth.persistance.entity.User
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Post (
    title: String,
    user: User
): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "title", nullable = false)
    val title = title

    @ManyToOne
    val user = user

}