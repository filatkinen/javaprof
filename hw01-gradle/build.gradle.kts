import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val guavaModule="33.2.1-jre"

plugins {
    id ("com.github.johnrengelman.shadow")
}

dependencies {
    implementation("com.google.guava:guava:$guavaModule")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("gradleHelloWorld")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.filatkinen.HelloOtus"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
