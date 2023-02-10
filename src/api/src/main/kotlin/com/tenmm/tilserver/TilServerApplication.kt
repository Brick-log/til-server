package com.tenmm.tilserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class TilServerApplication

fun main(args: Array<String>) {
    runApplication<TilServerApplication>(*args)
}
