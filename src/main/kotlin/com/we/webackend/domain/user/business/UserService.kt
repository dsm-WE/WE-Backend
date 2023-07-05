package com.we.webackend.domain.user.business

import com.we.webackend.domain.user.presentation.dto.request.EditUserRequest
import com.we.webackend.domain.user.presentation.dto.request.LoginRequest
import com.we.webackend.domain.user.presentation.dto.request.SignupRequest
import com.we.webackend.domain.user.presentation.dto.response.TokenResponse
import com.we.webackend.domain.user.presentation.dto.response.UserResponse
import org.springframework.web.multipart.MultipartFile

interface UserService {

    fun sendCode(email: String)
    fun checkCode(email: String, code: String): Boolean

    fun signup(request: SignupRequest, file: MultipartFile)
    fun login(request: LoginRequest): TokenResponse

    fun getMyInformation(email: String): UserResponse
    fun editMyInformation(email: String, request: EditUserRequest)


}
