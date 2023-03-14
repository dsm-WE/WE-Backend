package com.we.webackend.domain.post.presentation

import com.we.webackend.domain.post.business.PostService
import com.we.webackend.domain.post.presentation.dto.request.CreatePortfolioRequest
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/post")
class PostController(
    private val postService: PostService
) {

    @GetMapping("/list/")
    fun getMinimumPortfolioList(
        @RequestParam idx: Int,
        @RequestParam size: Int
    ): Page<MinimumPortfolioResponse> {
        return postService.getMinimumPortfolioList(idx, size)
    }

    @GetMapping
    fun getMaximumPortfolio(
        @RequestParam portfolioId: String
    ): MaximumPortfolioResponse {
        return postService.getMaximumPortfolio(portfolioId)
    }


    @PostMapping
    fun createPortfolio(
        @RequestBody request: CreatePortfolioRequest
    ) {
        return postService.createPortfolio(request)
    }

    @PostMapping("/file")
    fun createFile(
        @ModelAttribute(value = "fileList") fileList: List<MultipartFile>
    ) {
        return
    }

    @PatchMapping
    fun editPortfolio(
        @RequestBody request: EditPortfolioRequest
    ) {
        return postService.editPortfolio(request)
    }

    @DeleteMapping
    fun deletePortfolio(
        @RequestParam portfolioId: String
    ) {
        return postService.deletePortfolio(portfolioId)
    }


}