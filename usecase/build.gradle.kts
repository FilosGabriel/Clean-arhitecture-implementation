plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.domain)
    implementation(libs.junitJupiterApi)
    implementation(libs.junitJupiterEngine)
    testImplementation(libs.mockitoJunit)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.assertJ)

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}