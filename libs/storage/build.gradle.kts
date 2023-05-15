plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
}

dependencies {
    runtimeOnly("com.mysql:mysql-connector-j")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}
