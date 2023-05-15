plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.8.0"
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}
