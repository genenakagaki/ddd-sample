rootProject.name = "checklist"

// Configured using "Multi-Project Builds using buildSrc"
// https://docs.gradle.org/current/userguide/intro_multi_project_builds.html#1_multi_project_builds_using_buildsrc
include("web-app", "web", "app", "domain:core", "domain:shared", "repository", "query", "persistence")
