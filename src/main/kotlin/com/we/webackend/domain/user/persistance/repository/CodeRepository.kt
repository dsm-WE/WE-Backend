package com.we.webackend.domain.user.persistance.repository

import com.we.webackend.domain.user.persistance.entity.Code
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface CodeRepository: CrudRepository<Code, String> {

    fun findByEmail(email: String): Optional<Code>
}
