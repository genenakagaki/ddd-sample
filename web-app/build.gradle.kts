plugins {
    id("checklist.spring-conventions")
}

dependencies {
    implementation(project(":web"))
    implementation(project(":app"))
    implementation(project(":domain:core"))
    implementation(project(":domain:shared"))
    implementation(project(":repository"))
    implementation(project(":query"))
    implementation(project(":persistence"))
    implementation("org.springframework.boot:spring-boot-starter")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}