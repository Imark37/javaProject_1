plugins {
    id("java")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
    // Для вывода System.out.println() в консоль (для себя)
    testLogging {
        showStandardStreams = true
    }
}

    val testSource = sourceSets["test"]

    // --- ЗАДАЧА 1
    tasks.register<Test>("runTask1") {
        group = "other"

        testClassesDirs = testSource.output.classesDirs
        classpath = testSource.runtimeClasspath
        outputs.upToDateWhen { false }

        useJUnitPlatform {
            includeTags("task1")
        }
        testLogging {
            showStandardStreams = true
        }
    }

    // --- ЗАДАЧА 2
    tasks.register<Test>("runTask2") {
        group = "other"

        testClassesDirs = testSource.output.classesDirs
        classpath = testSource.runtimeClasspath
        outputs.upToDateWhen { false }

        useJUnitPlatform {
            includeTags("task2")
        }
        testLogging {
            showStandardStreams = true
        }
    }

    // --- Запускаем только NumberUtilsAssertionsTest
    tasks.register<Test>("runAssertions") {
        group = "other"
        testClassesDirs = tasks.test.get().testClassesDirs
        classpath = tasks.test.get().classpath
        outputs.upToDateWhen { false }
        useJUnitPlatform { includeTags("assertions") }
        testLogging {
            showStandardStreams = true
            events("passed", "skipped", "failed")
        }
    }


// Запускаем runTask2 и выводим "Test run is over"
tasks.register("printResult") {
    dependsOn("runTask2")
    doLast {
        println("Test run is over")
    }
}