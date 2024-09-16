plugins {
    id("checklist.spring-conventions")
    id("org.openapi.generator") version "7.8.0"
}

dependencies {
    implementation(project(":app"))
    implementation(project(":query"))
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

openApiGenerate {
    generatorName.set("typescript-fetch")
    remoteInputSpec.set("http://localhost:8080/v3/api-docs")
    outputDir.set("$rootDir/web-frontend/src/api/generated")
    configOptions.put("dateLibrary", "java8")
    cleanupOutput.set(true)
}
