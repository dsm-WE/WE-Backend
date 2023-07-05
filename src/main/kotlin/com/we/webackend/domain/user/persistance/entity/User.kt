package com.we.webackend.domain.user.persistance.entity

import com.we.webackend.domain.post.persistance.entity.File
import com.we.webackend.domain.user.presentation.dto.response.UserResponse
import com.we.webackend.domain.post.persistance.entity.Post
import com.we.webackend.domain.user.presentation.dto.request.EditUserRequest
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany


@Entity
class User(
    name: String,
    email: String,
    password: String,
    introduction: String,
    file: File
): BaseTimeEntity(), UserDetails {

    @Id
    val email: String = email

    @Column(name = "password", nullable = false)
    private val password = password

    @Column(name = "name", nullable = false)
    var name = name

    @OneToMany(mappedBy = "user")
    val postList: MutableList<Post> = ArrayList()

    val role: Role = Role.USER

    @Column(name = "introduction", nullable = false)
    var introduction: String = introduction

    @ManyToOne
    var file: File = file
        protected set

    fun toUserResponse(): UserResponse {
        return UserResponse(
            this.name,
            this.email,
            this.introduction,
            this.file.fileUrl
        )
    }

    fun edit(request: EditUserRequest) {
        request.name?.let {
            this.name = it
        }
        request.introduction?.let {
            this.introduction = it
        }
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(this.role.name))
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
