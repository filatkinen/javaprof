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
    runtimeOnly("org.aspectj:aspectjweaver:1.9.22.1")
    implementation("org.aspectj:aspectjrt:1.9.22.1")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("Demo")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.filatkinen.proxy.Demo"))
        }
    }

    build {
        dependsOn(shadowJar)
    }

    test{
        useJUnitPlatform()
    }
}
