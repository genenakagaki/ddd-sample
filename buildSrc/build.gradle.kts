plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("io.freefair.gradle:lombok-plugin:8.10")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.3.3")
    implementation("io.spring.gradle:dependency-management-plugin:1.1.6")
}