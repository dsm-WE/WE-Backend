package com.we.webackend.domain.user.business

import com.we.webackend.domain.user.persistance.repository.UserRepository
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.exception.data.ErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        return userRepository.findById(email?: throw BusinessException(errorCode = ErrorCode.UNDEFINED_ERROR)).orElse(null)
            ?: throw BusinessException(ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }
}
