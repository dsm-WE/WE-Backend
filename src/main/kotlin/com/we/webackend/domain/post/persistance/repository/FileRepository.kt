package com.we.webackend.domain.post.persistance.repository

import com.we.webackend.domain.post.persistance.entity.File
import com.we.webackend.domain.post.persistance.entity.Portfolio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface FileRepository: JpaRepository<File, Long> {

    @Query(value = "select * from file where portfolio_post_id = :portfolioId", nativeQuery = true)
    fun findByPortfolio(@Param("portfolioId") portfolioId: Long): Optional<Portfolio>

    @Query(value = "delete from file where portfolio_post_id = :portfolioId", nativeQuery = true)
    @Modifying
    fun deleteByPortfolio(@Param("portfolioId") portfolioId: Long)

}
