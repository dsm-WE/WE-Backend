package com.we.webackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
class WeBackendApplication

fun main(args: Array<String>) {
    runApplication<WeBackendApplication>(*args)
}
