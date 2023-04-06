package com.tenmm.crawler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
// import org.springframework.context.annotation.ComponentScan

@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = ["com.tenmm.tilserver", "com.tenmm.crawler"]
)
// @ComponentScan(basePackages = [])
class CrawlerApplication

fun main(args: Array<String>) {
    runApplication<CrawlerApplication>(*args)
}
