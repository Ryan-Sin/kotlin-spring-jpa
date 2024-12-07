package com.ryan.kotlinspirngjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SpringJpaExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringJpaExampleApplication>(*args)
}
