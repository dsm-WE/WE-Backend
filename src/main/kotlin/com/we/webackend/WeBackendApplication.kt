package com.we.webackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeBackendApplication

fun main(args: Array<String>) {
    runApplication<WeBackendApplication>(*args)
}
