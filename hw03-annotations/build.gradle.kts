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

//tasks.test {
//    useJUnitPlatform()
//}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("annotationLouncher")
        archiveVersion.set("0.3")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.filatkinen.annotations.launcher.Main"))
        }
    }

    build {
        dependsOn(shadowJar)
    }

    test{
        useJUnitPlatform()
    }
}
