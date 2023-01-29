package com.tenmm.tilserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TilServerApplication

fun main(args: Array<String>) {
    runApplication<TilServerApplication>(*args)
}
