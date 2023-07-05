package com.we.webackend.domain.post.presentation

import com.we.webackend.domain.post.business.PostService
import com.we.webackend.domain.post.presentation.dto.request.CreatePortfolioRequest
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import com.we.webackend.global.exception.data.ErrorCode
import com.we.webackend.global.exception.data.BusinessException
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/post")
class PostController(
    private val postService: PostService
) {

    @GetMapping("/list")
    fun getMinimumPortfolioList(
        @RequestParam(defaultValue = "0") idx: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<MinimumPortfolioResponse> {
        return postService.getMinimumPortfolioList(idx, size)
    }

    @GetMapping
    fun getMaximumPortfolio(
        @RequestParam portfolioId: Long
    ): MaximumPortfolioResponse {
        return postService.getMaximumPortfolio(portfolioId)
    }


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createPortfolio(
        @RequestPart request: CreatePortfolioRequest,
        @RequestPart file: MultipartFile,
        @AuthenticationPrincipal user: UserDetails?
    ) {
        return postService.createPortfolio(request, file, user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR))
    }


    @PatchMapping
    fun editPortfolio(
        @RequestParam portfolioId: Long,
        @RequestBody request: EditPortfolioRequest,
        @AuthenticationPrincipal user: UserDetails?
    ) {
        return postService.editPortfolio(portfolioId, request, user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR))
    }

    @PatchMapping("/file")
    fun changePortfolioFile(
        @RequestParam portfolioId: Long,
        @RequestPart file: MultipartFile,
        @AuthenticationPrincipal user: UserDetails?
    ) {
        return postService.changePortfolioFile(portfolioId, file, user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR))
    }

    @DeleteMapping
    fun deletePortfolio(
        @RequestParam portfolioId: Long,
        @AuthenticationPrincipal user: UserDetails?
    ) {
        return postService.deletePortfolio(portfolioId, user?.username?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR))
    }


}
