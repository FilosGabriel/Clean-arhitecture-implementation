plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

version = "unspecified"

repositories {
    mavenCentral()
}
val vertxVersion = "4.0.3"

dependencies {
    implementation(projects.config)
    implementation(projects.domain)
    implementation(projects.usecase)
    implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
    implementation("io.vertx:vertx-web")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}