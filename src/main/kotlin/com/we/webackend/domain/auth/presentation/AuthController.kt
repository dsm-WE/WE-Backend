package com.we.webackend.domain.auth.presentation

import com.we.webackend.domain.auth.business.AuthService
import com.we.webackend.domain.auth.presentation.dto.request.CheckCodeRequest
import com.we.webackend.domain.auth.presentation.dto.request.LoginRequest
import com.we.webackend.domain.auth.presentation.dto.request.SignupRequest
import com.we.webackend.domain.auth.presentation.dto.response.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PutMapping("/code")
    fun sendCode(@RequestParam email: String) {
        return authService.sendCode(email)
    }

    @PostMapping("/code")
    fun checkCode(@RequestBody request: CheckCodeRequest): Boolean {
        return authService.checkCode(request.email, request.code)
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestBody request: SignupRequest
    ) {
        return authService.signup(request)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): TokenResponse {
        return authService.login(request)
    }

}