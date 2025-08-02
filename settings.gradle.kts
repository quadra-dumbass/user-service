pluginManagement {
    val githubUser: String? = System.getenv("GITHUB_ACTOR") ?: providers.gradleProperty("gpr.user").orNull
    val githubToken: String? = System.getenv("GITHUB_TOKEN") ?: providers.gradleProperty("gpr.key").orNull
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/quadra-dumbass/convention-plugins")
            credentials {
                username = githubUser
                password = githubToken
            }
        }
    }
}

rootProject.name = "user-service"
