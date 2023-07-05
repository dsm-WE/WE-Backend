package com.we.webackend.domain.post.business

import com.we.webackend.domain.post.persistance.entity.File
import com.we.webackend.domain.user.persistance.repository.UserRepository
import com.we.webackend.domain.post.persistance.entity.Portfolio
import com.we.webackend.domain.post.persistance.repository.FileRepository
import com.we.webackend.domain.post.persistance.repository.PortfolioRepository
import com.we.webackend.domain.post.persistance.repository.PostRepository
import com.we.webackend.domain.post.presentation.dto.request.CreatePortfolioRequest
import com.we.webackend.domain.post.presentation.dto.request.EditPortfolioRequest
import com.we.webackend.domain.post.presentation.dto.response.MaximumPortfolioResponse
import com.we.webackend.domain.post.presentation.dto.response.MinimumPortfolioResponse
import com.we.webackend.global.exception.data.ErrorCode
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.file.FileUploader
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional(readOnly = true)
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val portfolioRepository: PortfolioRepository,
    private val fileUploader: FileUploader,
    private val userRepository: UserRepository,
    private val fileRepository: FileRepository
): PostService {

    override fun getMinimumPortfolioList(idx: Int, size: Int): Page<MinimumPortfolioResponse> {
        return portfolioRepository.findAll(PageRequest.of(idx, size, Sort.by("createdAt").descending())).map {
            it.toMinimumPortfolioResponse()
        }
    }

    override fun getMaximumPortfolio(portfolioId: Long): MaximumPortfolioResponse {
        return portfolioRepository.findById(portfolioId).orElse(null)?.let {
            it.toMaximumPortfolioResponse()
        }?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
    }

    @Transactional
    override fun createPortfolio(request: CreatePortfolioRequest, file: MultipartFile, userEmail: String) {
        val user = userRepository.findByIdOrNull(userEmail)?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)

        val file = fileRepository.save(
            File(
                fileUploader.uploadFile("$userEmail/${request.title}", file),
                file.name
            )
        )

        val portfolio = portfolioRepository.save(
            Portfolio(
                request.title,
                user,
                request.content,
                file
            )
        )
        file.changePortfolio(portfolio)
    }

    @Transactional
    override fun editPortfolio(portfolioId: Long, request: EditPortfolioRequest, userEmail: String) {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId)?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (portfolio.user.email == userEmail) {
            portfolio.edit(request)
        } else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @Transactional
    override fun changePortfolioFile(portfolioId: Long, file: MultipartFile, userEmail: String) {
        val portfolio = portfolioRepository.findByIdOrNull(portfolioId)?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        if (portfolio.user.email == userEmail) {
            val file = fileRepository.save(
                File(
                    fileUploader.uploadFile("$userEmail/${portfolio.title}", file),
                    file.name
                )
            )

            val portfolio = portfolioRepository.findById(portfolioId)
                .orElse(null)?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)

            portfolio.changePortfolioFile(file)
        } else throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR)
    }

    @Transactional
    override fun deletePortfolio(portfolioId: Long, userEmail: String) {
        val portfolio = postRepository.findByIdAndUser(portfolioId, userEmail).orElse(null)?: throw BusinessException(errorCode = ErrorCode.PERSISTENCE_DATA_NOT_FOUND_ERROR)
        postRepository.deleteByPostId(portfolio.id!!)
    }


}
