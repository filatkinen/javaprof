import  com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id ("com.github.johnrengelman.shadow")
}

group = "ru.otus.filatkinen"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("ch.qos.logback:logback-classic")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("Calc")
        archiveVersion.set("0.2")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.filatkinen.calculator.CalcDemo"))
        }
    }

    build {
        dependsOn(shadowJar)
    }

    test{
        useJUnitPlatform()
    }
}
