package com.we.webackend.domain.post.business

import com.we.webackend.domain.post.presentation.dto.request.CreatePortfolioRequest
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile

interface PostService {

    fun getMinimumPortfolioList(idx: Int, size: Int): Page<MinimumPortfolioResponse>

    fun getMaximumPortfolio(portfolioId: Long): MaximumPortfolioResponse


    fun createPortfolio(request: CreatePortfolioRequest, file: MultipartFile, userEmail: String)

    fun editPortfolio(portfolioId: Long, request: EditPortfolioRequest, userEmail: String)

    fun changePortfolioFile(portfolioId: Long, file: MultipartFile, userEmail: String)

    fun deletePortfolio(portfolioId: Long, userEmail: String)

}
