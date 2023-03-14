package com.we.webackend.domain.post.business

import com.we.webackend.domain.post.persistance.entity.Portfolio
import com.we.webackend.domain.post.persistance.repository.PortfolioRepository
import com.we.webackend.domain.post.presentation.dto.request.CreatePortfolioRequest
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class PostServiceImpl(
    private val portfolioRepository: PortfolioRepository
): PostService {

    override fun getMinimumPortfolioList(idx: Int, size: Int): Page<MinimumPortfolioResponse> {
        return portfolioRepository.findAll(PageRequest.of(idx, size, Sort.by("createdAt").descending())).map {
            it.toMinimumPortfolioResponse()
        }
    }

    override fun getMaximumPortfolio(portfolioId: Long): MaximumPortfolioResponse {
        return portfolioRepository.findById(portfolioId).orElse(null)?: throw
    }

    override fun createPortfolio(request: CreatePortfolioRequest) {
        return portfolioRepository.save(
            Portfolio(
                title = 
            )
        )
    }

    override fun createFile(fileList: List<MultipartFile>) {
        TODO("Not yet implemented")
    }

    override fun editPortfolio(request: EditPortfolioRequest) {
        TODO("Not yet implemented")
    }

    override fun deletePortfolio(portfolioId: String) {
        TODO("Not yet implemented")
    }
}