package com.we.webackend.domain.post.persistance.entity

import com.we.webackend.domain.user.persistance.entity.BaseTimeEntity
import com.we.webackend.domain.user.persistance.entity.User
import javax.persistence.*

@Entity
@Table(name = "post")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type")
abstract class Post (
    title: String,
    user: User
): BaseTimeEntity() {

    @Id
    @Column(name = "post_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "title", nullable = false)
    var title = title

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    val user = user

    @Column(name = "like_count", nullable = false)
    val likeCount: Int = 0

}
