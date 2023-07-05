package com.we.webackend.domain.user.business

import com.we.webackend.domain.post.persistance.entity.File
import com.we.webackend.domain.post.persistance.repository.FileRepository
import com.we.webackend.domain.user.persistance.entity.Code
import com.we.webackend.domain.user.persistance.entity.User
import com.we.webackend.domain.user.persistance.repository.CodeRepository
import com.we.webackend.domain.user.persistance.repository.UserRepository
import com.we.webackend.domain.user.presentation.dto.request.EditUserRequest
import com.we.webackend.domain.user.presentation.dto.request.LoginRequest
import com.we.webackend.domain.user.presentation.dto.request.SignupRequest
import com.we.webackend.domain.user.presentation.dto.response.TokenResponse
import com.we.webackend.domain.user.presentation.dto.response.UserResponse
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.exception.data.ErrorCode
import com.we.webackend.global.file.FileUploader
import com.we.webackend.global.security.TokenProvider
import com.we.webackend.infra.email.EmailUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*


@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val emailUtil: EmailUtil,
    private val codeRepository: CodeRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val fileRepository: FileRepository,
    private val fileUploader: FileUploader
): UserService {

    @Transactional
    override fun sendCode(email: String) {
        val code = Random().nextInt(1000, 9999).toString()
        codeRepository.save(
            Code(
                email, code
            )
        )
        emailUtil.sendEmail(email,
            "WE Email Authentication Code",
            "The Email AUthentication Code is $code")
    }

    override fun checkCode(email: String, code: String): Boolean {
        if (codeRepository.findByEmail(email).orElse(null)?.code == code) return true
        return false
    }

    @Transactional
    override fun signup(request: SignupRequest, file: MultipartFile) {
        if (checkCode(request.email, request.code)) {
            val file = fileRepository.save(
                File(
                    fileUploader.uploadFile("${request.email}/profile", file),
                    file.name
                )
            )

            userRepository.save(
                User(
                    request.name,
                    request.email,
                    passwordEncoder.encode(request.password),
                    request.introduction,
                    file
                )
            )
        } else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    override fun login(request: LoginRequest): TokenResponse {
        val user = userRepository.findById(request.email).orElse(null)
            ?: throw BusinessException(ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (passwordEncoder.matches(request.password, user.password)) {
            return tokenProvider.encode(user.email)
        } else throw BusinessException(ErrorCode.NOT_MATCHED_ERROR)
    }

    override fun getMyInformation(email: String): UserResponse {
        return (userRepository.findById(email).orElse(null)
            ?: throw BusinessException(ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
            .toUserResponse()
    }

    @Transactional
    override fun editMyInformation(email: String, request: EditUserRequest) {
        val user = (userRepository.findById(email).orElse(null)
            ?: throw BusinessException(ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR))
        user.edit(request)
    }

}
