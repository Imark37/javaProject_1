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
}

tasks.test {
    useJUnitPlatform()
    // Для вывода System.out.println() в консоль (для себя)
    testLogging {
        showStandardStreams = true
    }
}
/*
// --- ЗАДАЧА 1 (запускаем тесты с тегом task1)
tasks.register<Test>("runTask1") {
    group = "other" // появится в списке задач под этим разделом
    useJUnitPlatform {
        includeTags("task1")
    }
}

// --- ЗАДАЧА 2 (запускаем тесты с тегом task2)
tasks.register<Test>("runTask2") {
    group = "other"
    useJUnitPlatform {
        includeTags("task2")
    }
}
*/

//**
// Я не совсем понял, почему вариант из лекции (описан выше) по тегам не работал. При запуске не находил тесты
// При запуске через -info получал (NO-SOURCE).
// Пришлось обратиться к ИИ,там получил ответ, что Gradle в этой конкретной точке не видит контейнер SourceSetContainer.
// Чтобы исправить это, нужно явно обернуть задачи в блок, который дождется применения плагина Java.
// Но все таки не очень понимаю, почему так работает.
// **//

plugins.withType<JavaPlugin> {
    val testSource = extensions.getByType<SourceSetContainer>()["test"]

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
}


// Запускаем runTask2 и выводим "Test run is over"
tasks.register("printResult") {
    dependsOn("runTask2")
    doLast {
        println("Test run is over")
    }
}