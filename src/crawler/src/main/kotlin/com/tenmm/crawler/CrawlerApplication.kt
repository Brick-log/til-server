package com.tenmm.crawler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class CrawlerApplication

fun main(args: Array<String>) {
    runApplication<CrawlerApplication>(*args)
}
