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

//jib {
//    from {
//        image = "amazoncorretto:21-alpine"
////        platforms {
////            platform {
////                architecture = "arm64v8"
////                os = "linux"
////            }
////        }
//    }
//    to {
//        image = "localhost:5000/my-image/built-with-jib"
//    }
//    container {
//        jvmFlags = listOf("-Dmy.property=example.value", "-Xms512m", "-Xdebug")
//        mainClass = "com.genenakagaki.checklist.ChecklistWebApplication"
//        ports = listOf("8080", "8080")
//        format = ImageFormat.Docker
//    }
//    dockerClient {
//        executable = "/usr/local/bin/docker"
//    }
//}