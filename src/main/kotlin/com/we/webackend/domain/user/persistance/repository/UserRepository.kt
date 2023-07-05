package com.we.webackend.domain.user.persistance.repository

import com.we.webackend.domain.user.persistance.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}
