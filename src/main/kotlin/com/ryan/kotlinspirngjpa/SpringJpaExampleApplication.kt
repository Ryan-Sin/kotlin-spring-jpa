package com.ryan.kotlinspirngjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJpaExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringJpaExampleApplication>(*args)
}
