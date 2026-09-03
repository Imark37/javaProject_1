plugins {
    id("java")
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

// 1. запускаем все тесты
tasks.register("runTests") {
    dependsOn("test")
}

// 2. выводим "Test run is over" после завершения runTests
tasks.register("printResult") {
    dependsOn("runTests")
    doLast {
        println("Test run is over")
    }
}