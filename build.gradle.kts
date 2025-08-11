plugins {
    id("com.quadra.spring-conventions") version "1.0.0"
}

group = "com.quadra"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Web MVC
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Used to implement jwt-related functionality
    implementation("com.auth0:java-jwt:4.4.0")

    // Spring DB-related libraries
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    // implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Spring Security & OAuth2
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.security:spring-security-test")

    // Database for local environment and test
    runtimeOnly("com.h2database:h2")

    // MariaDB for dev
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // Redis for testing only
    // testImplementation("it.ozimov:embedded-redis:0.7.3")
}