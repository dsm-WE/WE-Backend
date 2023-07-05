package com.we.webackend.global.security.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.we.webackend.domain.user.business.CustomUserDetailsService
import com.we.webackend.global.exception.filter.ExceptionFilter
import com.we.webackend.global.security.TokenProvider
import com.we.webackend.global.security.filter.JwtFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfiguration(
    private val tokenProvider: TokenProvider,
    private val customAuthDetailsService: CustomUserDetailsService,
    private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val jwtFilter = JwtFilter(tokenProvider, customAuthDetailsService)
        val exceptionFilter = ExceptionFilter(objectMapper)
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(exceptionFilter, JwtFilter::class.java)
    }
}
