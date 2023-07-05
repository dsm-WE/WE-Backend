package com.we.webackend.domain.user.presentation

import com.we.webackend.domain.user.business.UserService
import com.we.webackend.domain.user.presentation.dto.request.CheckCodeRequest
import com.we.webackend.domain.user.presentation.dto.request.EditUserRequest
import com.we.webackend.domain.user.presentation.dto.request.LoginRequest
import com.we.webackend.domain.user.presentation.dto.request.SignupRequest
import com.we.webackend.domain.user.presentation.dto.response.TokenResponse
import com.we.webackend.domain.user.presentation.dto.response.UserResponse
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.exception.data.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PutMapping("/code")
    fun sendCode(@RequestParam email: String) {
        return userService.sendCode(email)
    }

    @PostMapping("/code")
    fun checkCode(@RequestBody request: CheckCodeRequest): Boolean {
        return userService.checkCode(request.email, request.code)
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(
        @RequestPart request: SignupRequest,
        @RequestPart file: MultipartFile
    ) {
        return userService.signup(request, file)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): TokenResponse {
        return userService.login(request)
    }


    @GetMapping("/my")
    fun getMyInformation(
        @AuthenticationPrincipal user: UserDetails?
    ): UserResponse {
        return userService.getMyInformation(
            user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
        )
    }

    @PatchMapping("/my")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun editMyInformation(
        @AuthenticationPrincipal user: UserDetails?,
        @RequestBody request: EditUserRequest
    ) {
        userService.editMyInformation(
            user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
            , request
        )
    }

}
