import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "kotlin-kapt")

    group = "com.tenmm"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2020.0.3")
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//    runtimeOnly("com.mysql:mysql-connector-j")
//    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

        // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
        implementation("org.apache.commons:commons-lang3:3.12.0")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }


    ktlint {
        disabledRules.set(setOf("import-ordering"))
    }
}
