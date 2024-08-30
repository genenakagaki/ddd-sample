plugins {
    id("checklist.spring-conventions")
}

dependencies {
    implementation(project(":domain:shared"))
    implementation(project(":persistence"))
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
