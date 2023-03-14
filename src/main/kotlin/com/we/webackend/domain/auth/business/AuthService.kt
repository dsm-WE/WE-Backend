package com.we.webackend.domain.auth.business

import com.we.webackend.domain.auth.presentation.dto.request.LoginRequest
import com.we.webackend.domain.auth.presentation.dto.request.SignupRequest
import com.we.webackend.domain.auth.presentation.dto.response.TokenResponse

interface AuthService {

    fun sendCode(email: String)
    fun checkCode(email: String, code: String): Boolean

    fun signup(requst: SignupRequest)
    fun login(request: LoginRequest): TokenResponse

    fun getMyInfo()


}