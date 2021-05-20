plugins {
    `java-library`
    id("io.freefair.lombok") version "6.0.0-m2"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.jakartaValidation)
}

