import org.gradle.kotlin.dsl.invoke
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "2.0.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
//    testLogging{
//        events "passed", "failed"
//    }
    useJUnitPlatform()
}

tasks.withType<Test>{

}





kotlin {
    jvmToolchain(17)
}