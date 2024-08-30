plugins {
    id("checklist.spring-conventions")
    id("java-library")
}

dependencies {
    api(project(":domain:shared"))
    implementation(project(":domain:core"))
    implementation("org.springframework.boot:spring-boot-starter")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}