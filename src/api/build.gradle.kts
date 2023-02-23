plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":protocol"))
    // https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webflux-ui
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.2")
}
