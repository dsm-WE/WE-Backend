package com.we.webackend.global.security.filter

import com.we.webackend.domain.user.business.CustomUserDetailsService
import com.we.webackend.global.exception.data.BusinessException
import com.we.webackend.global.exception.data.ErrorCode
import com.we.webackend.global.security.TokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    private val tokenProvider: TokenProvider,
    private val customAuthDetailsService: CustomUserDetailsService
): OncePerRequestFilter() {

    companion object{
        const val AUTH = "Authorization"
    }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val subject: String? = getToken(request)?.let{return@let tokenProvider.getSubjectWithExpiredCheck(it)}
        subject?.let{
            val userDetails = customAuthDetailsService.loadUserByUsername(it)
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, it, userDetails.authorities)
        }
        filterChain.doFilter(request, response)
    }

    private fun getToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTH) ?: return null
        if (bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR, bearerToken)
    }
}
