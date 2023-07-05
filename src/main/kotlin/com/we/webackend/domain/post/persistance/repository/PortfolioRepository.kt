package com.we.webackend.domain.post.persistance.repository

import com.we.webackend.domain.post.persistance.entity.Portfolio
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioRepository: JpaRepository<Portfolio, Long> {
}